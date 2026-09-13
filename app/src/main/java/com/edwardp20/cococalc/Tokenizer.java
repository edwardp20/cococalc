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
        //找出加号的位置
        int indexOfPlus = expr.indexOf('+');
        //找出操作数一
        String number1 = expr.substring(0,indexOfPlus);
        //找出操作数二
        String number2 = expr.substring(indexOfPlus + 1);
        //将加号,操作数一，二存入ArrayList
        result.add(0,number1);
        result.add(1,expr.substring(indexOfPlus,indexOfPlus + 1));
        result.add(2,number2);
        //返回ArrayList
        return result;
    }
}