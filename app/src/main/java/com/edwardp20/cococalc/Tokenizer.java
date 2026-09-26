package com.edwardp20.cococalc;
/**
* Tokenizer
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
import java.util.ArrayList;

public class Tokenizer {
    public static ArrayList<String> divideExpression(String expr) {
        //定义ArrayList
        ArrayList<String> result = new ArrayList<String>();
        //分词循环
        int i = 0;
        //当i<expr.length()时，循环
        while(i < expr.length()) {
            //循环提取每一位
            char character = expr.charAt(i);
            /*character
             6767676767
             +++++++
             
             */
            //如果这一位是数字
            if(Character.isDigit(character)) {
                //那么，我们先新建一个StringBuilder
                StringBuilder sb = new StringBuilder();
                //当不超过数组范围时，并且这一位仍然是数字,or这一位是小数点
                while((i < expr.length()) && (Character.isDigit(i) || (i == '.'))) {
                    //那么就把这个存到string builder中
                    sb.append(character);
                    //后一位
                    i++;
                }
                //然后将这个数字存入结果
                result.add(sb.toString());
                //直接进入，下次循环
                continue;
            } else if((i == '+') || (i == '-')) {
                //如果是运算符，将运算符存入结果
                result.add(String.valueOf(i));
                i++;
            }
        }
        //返回ArrayList
        return result;
    }
}