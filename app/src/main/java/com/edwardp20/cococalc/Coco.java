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
import java.util.Scanner;
import java.util.ArrayList;

public class Coco {
    public static void main(String[] args) {
        //Scanner
        Scanner s = new Scanner(System.in);
        //计算器主循环
        while(true) {
            //shell
            System.out.print("cococalc>");
            //收命令
            String command = s.next();
            /*用户输入
            那么交给processor()处理并返回结果
            */
            System.out.println(processor(command));
        }
    }

    private static String processor(String command) {
        //如果匹配到--exit，就直接退出
        //定义返回值
        String result = "= ";
        BigDecimal tmp = null;
        if(command.equals("--exit")) {
            System.exit(0);
        }
        //分词
        ArrayList<String> exprArr = Tokenizer.divideExpression(command);
        //计数器
        //TODO:实现调度场算法
        int count=0;
        //
        //我们来提取这个运算符和操作数
        while(count < exprArr.size()) {
            //如果是第一位
            if(count == 0) {
                //将第一位作为结果
                tmp = new BigDecimal(exprArr.get(count));
                //update count
                count++;
                continue;
            } else {
                //获取运算符，操作数
                String op = exprArr.get(count);
                count++;
                BigDecimal num = new BigDecimal(exprArr.get(count));
                count++;
                //计算
                if(op.equals("+")) {
                    tmp = tmp.add(num);
                } else if(op.equals("-")) {
                    tmp = tmp.subtract(num);
                }
                continue;
            }
        }
        result = tmp.toPlainString();
        /*存入操作数一二
        BigDecimal num1 = new BigDecimal(exprArr.get(0));
        BigDecimal num2 = new BigDecimal(exprArr.get(2));
        //加
        result = num1.add(num2).toPlainString();*/
        return result;
    }
}