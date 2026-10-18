package com.edwardp20.cococalc.util;
/**
* NumberUtils
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

public class NumberUtils {
    //用于判断字符串是否是数字
    public static boolean isNumber(String str) {
        //如果str为null或啥也没有，直接false
        if((str == null) || (str.isEmpty())) {
            return false;
        } else {//否则看看能否创BigDecimal instance
            try {
                new BigDecimal(str);
                return true;//是数字
            } catch(NumberFormatException e) {
                return false;
            }
        }
    }
}