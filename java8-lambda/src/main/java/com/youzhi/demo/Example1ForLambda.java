package com.youzhi.demo;


import java.util.*;
import java.util.function.BinaryOperator;

public class Example1ForLambda {
    /**
     * 总结：
     *  1.简化某些匿名内部类的写法，但并不能取代所有的匿名内部类，只能取代 函数接口 的简写
     *  2.函数式编程，可以实现函数接口，将该函数作为另一个函数的参数。（例子在资料链接中）
     */

    public static void main(String[] args) {
        /**
         * 使用lambda表达式取代某些匿名内部类
         */

        //1.无参函数的简写
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread run()");
            }
        }).start();
        //可以简化为如下形式
        new Thread(()-> System.out.println("Thread run()")).start();


        //2.带参函数的简写
        List<String> list = Arrays.asList("I","love","you","too");
        Collections.sort(list, new Comparator<String>() { //接口名
            @Override
            public int compare(String s1, String s2) { //方法名
                if(s1 == null){
                    return -1;
                }
                if(s2 == null){
                    return 1;
                }
                return s1.length() -s2.length();
            }
        });
        //可简化为如下形式
        List<String> list2 = Arrays.asList("I","love","you","too");
        Collections.sort(list,(s3,s4)->{
            if(s3 == null){
                return -1;
            }
            if(s4 == null){
                return 1;
            }
            return s3.length() -s4.length();
        });
    }
}
