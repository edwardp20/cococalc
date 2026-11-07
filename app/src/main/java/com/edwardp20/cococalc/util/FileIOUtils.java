package com.edwardp20.cococalc.util;
/**
* FileIOUtils
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
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;

import java.io.IOException;

public class FileIOUtils {
    /*
        写文件，并且append
    */
    public static boolean writeButAppend(String fileName,String stringToWrite) {
        //先创建目前的path
        Path currentPath = Path.of(fileName);
        //尝试去往文件里面追加
        try {
            Files.writeString(currentPath,stringToWrite,StandardOpenOption.APPEND);
            return true;//追加成功就返TRUE
        } catch(IOException e) {//追加失败就返回FALSE
            return false;
        }
    }
    /*
        写文件，但不追加
    */
    public static boolean write(String fileName,String stringToWrite) {
        Path currentPath = Path.of(fileName);//先创建一个path
        try {
            Files.writeString(currentPath,stringToWrite);
            return true;//能就返回true
        } catch(IOException e) {
            return false;
        }
    }

    /*
        读取文件
    */

    public static String read(String fileName) {
        Path currentPath = Path.of(fileName);
        if(!(Files.exists(currentPath))) {//如果压根没有这个文件，就直接返回空字符串
            return "";
        }
        try {
            String currentStringWhenReturn = Files.readString(currentPath);//如果有文件，而且没有出现读写错误的话，就返回正常字符串
            return currentStringWhenReturn;
        } catch(IOException e) {//否则就返回空字符串
            return "";
        }
    }
}