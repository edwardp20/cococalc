package com.edwardp20.cococalc;
/**
* Coco
* 
* Copyright (C) 2026 edwardp20 <zhangxixi201268@outlook.com>
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.HashMap;

import com.edwardp20.cococalc.util.NumberUtils;
import com.edwardp20.cococalc.util.CacheUtils;

public class Coco {
    public static void main(String[] args) {
        //Scanner
        Scanner s = new Scanner(System.in);
        //HashMap
        HashMap<String,String> cacheMap = new HashMap<String,String>();
        //计算器主循环
        while(true) {
            //shell
            System.out.print("cococalc>");
            //收命令
            String command = s.nextLine();
            if(command.equals("--exit")) {
                CacheUtils.writeJsonFileFromHashMap("cococalc-cache-unstable.json",mapToStore);
                System.exit(0);
            }
            /*用户输入
            那么交给processor()处理并返回结果
            */
            String res = processor(command);
            //提前看返回码
            if(res.equals("ExceptionDivideZero")) {//除以零
                continue;
            } else if(res.equals("ExceptionOp")) {//运算符连起来了
                continue;
            } else if(res.equals("ExceptionIllegalCharacter")) {
                continue;
            } else if(res.equals("ExceptionBracketUnmatched")) {
                continue;
            } else if(res.equals("ExceptionOpWrite")) {
                continue;
            } else if(res.equals("PrintVersion")) {
                continue;
            }
            System.out.println(res);//否则才打印
            //add to cache(test)
            cacheMap.put(command,res);
        }
    }

    private static String processor(String command) {
        //如果匹配到--exit，就直接退出
        //定义返回值
        String result = "= ";
        BigDecimal tmp = null;
        if(command.equals("--version")) {
            System.out.println("1.0");
            return "PrintVersion";
        }
        //分词
        ArrayList<String> exprArr = Tokenizer.divideExpression(command);
        if(exprArr.get(0).equals("ExceptionIllegalCharacter")) {
            System.out.println("非法字符");
            return "ExceptionIllegalCharacter";
        }
        //计数器
        //TODO:实现调度场算法 --
        //实现调度场
        ArrayList<String> yardArr = Yard.yard(exprArr);
        System.out.println(yardArr);
        if(yardArr.get(0).equals("ExceptionBracketUnmatched")) {
            System.out.println("括号不匹配");
;            return "ExceptionBracketUnmatched";
        }
        //初始化一个，计算队列
        ArrayDeque<BigDecimal> calcStack = new ArrayDeque<>();
        for(int i = 0;i < yardArr.size();i++) {
            String str = yardArr.get(i);
            //是数字直接压栈
            if(NumberUtils.isNumber(str)) {
                calcStack.push(new BigDecimal(str));
            } else {//否则弹两个操作数
                BigDecimal right = calcStack.pop();
                BigDecimal left = null;
                try {//如果左边弹不出来说明是运算符连起来了
                    left = calcStack.pop();
                } catch(NoSuchElementException e) {
                    System.out.println("语法错误");
                    return "ExceptionOp";
                }
                BigDecimal ans = null;
                //计算罗辑
                if(str.equals("+")) {
                    ans = left.add(right);
                } else if(str.equals("-")) {
                    ans = left.subtract(right);
                } else if(str.equals("*")) {
                    ans = left.multiply(right);
                } else if(str.equals("/")) {
                    try {
                       ans = left.divide(right);
                    } catch(ArithmeticException e) {
                        if(right.toPlainString().equals("0")) {
                            System.out.println("不能除以零");
                            result = "ExceptionDivideZero";
                            break;
                        }
                        ans = left.divide(right,10,RoundingMode.HALF_UP);//暂定保留十位
                    }
                }
                calcStack.push(ans);
            }
        }
        if(result.equals("ExceptionDivideZero")) {
            return result;
        }
        if(calcStack.size() > 1) {
            System.out.println("少写运算符");
            return "ExceptionOpWrite";
        }
        return (result + (calcStack.pop().toPlainString()));
    }
}