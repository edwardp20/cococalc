package com.edwardp20.cococalc.util;
/**
* CacheUtils
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
import java.util.HashMap;

import java.nio.file.Path;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class CacheUtils {
    /*
        用于读取缓存json的方法
        接收一个文件路径
    */
    public static HashMap<String,String> loadCacheFromJsonFile(String currentPath) {
        //创建一个新的Gson实例
        Gson tokenizer = new Gson();
        //读取文件
        String cacheJsonStr = FileIOUtils.read(currentPath);
        //创建一个typetoken开始分词
        Type typeOfHashMap = new TypeToken<HashMap<String,String>>(){}.getType();//创建一个匿名class用来告诉gson
        HashMap<String,String> lastMap = tokenizer.fromJson(cacheJsonStr,typeOfHashMap);
        return lastMap;
    }

    /*
        用于将哈希表转换为json文件
        接收一个哈希表，返回布尔
    */
    public static boolean writeJsonFileFromHashMap(String currentPath,HashMap<String,String> mapToConvert) {
        //创建一个美丽的Gson
        Gson prettyJsoner = new GsonBuilder().setPrettyPrinting().create();
        //开始转换
        String jsonStringToWrite = prettyJsoner.toJson(mapToConvert);
        //写入文件
        FileIOUtils.write(currentPath,jsonStringToWrite);
        return true;//暂时不想写错误判断，先这样
    }
}