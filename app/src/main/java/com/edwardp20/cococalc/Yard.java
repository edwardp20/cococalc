package com.edwardp20.cococalc;
/**
* Yard
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
import java.util.ArrayDeque;
import java.util.HashMap;
import java.math.BigDecimal;
import com.edwardp20.cococalc.util.NumberUtils;

public class Yard {
    public static HashMap<String,Integer> prec = new HashMap<>();
    static {
        prec.put("+", 1);
        prec.put("-", 1);
        prec.put("*", 2);
        prec.put("/", 2);
    }
    public static ArrayList<String> yard(ArrayList<String> exprDivided) {
        //初始化临时栈和结果
        ArrayDeque<String> tmpStack = new ArrayDeque<>();
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0;i < exprDivided.size();i++) {
            String s = exprDivided.get(i);
            if(NumberUtils.isNumber(s)) {
                result.add(s);
            } else {
                if(!(tmpStack.isEmpty())) {
                    while((!(tmpStack.isEmpty())) && ((prec.get(tmpStack.peek()).intValue()) >= (prec.get(s).intValue()))) {
                         result.add(tmpStack.pop());
                    }
                    tmpStack.push(s);
                } else {
                    tmpStack.push(s);
                }
            }
        }
        while(!(tmpStack.isEmpty())) {
            result.add(tmpStack.pop());
        }
        return result;
    }
}
