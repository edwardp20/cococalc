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
import java.util.Scanner;

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
        if(command.equals("--exit")) {
            System.exit(0);
        }
        //否则就暂时返回，测试文本
        return "test";
    }
}