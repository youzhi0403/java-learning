package com.youzhi.demo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleForStreamAPIPartThree {
    public static void main(String[] args) {
        /**
         * 知识点1：
         * Function.identity()
         * (1)Function是一个接口，接口中可以有两种具体的方法(已实现的方法，新特性)，default方法和static方法，identity()是静态方法。
         * (2)Function.identity()返回一个输入和输出一样的lambda表达式，等价于t -> t。
         * 知识点2：
         * String::length的语法形式叫做方法引用
         * (1)作用：这种语法用来替代某些特定形式的Lambda表达式(如果lambda表达式的全部内容是调用一个已有的方法)
         * (2)可以细分为4类：
         *      <1>引用静态方法：Integer::sum
         *      <2>引用某个对象的方法  list::add
         *      <3>引用某个类的方法 String::length
         *      <4>引用构造方法 HashMap::new
         * (3)Collector(收集器)
         *
         */

        Integer i;
        String s;

        /**
         * 将Stream转换为List
         */
        Stream stream = Stream.of("I","love","you","too");
        List<String> list = (List<String>) stream.collect(Collectors.toList());

        /**
         * 将Stream转换为Set
         */
        Stream stream2 = Stream.of("I","love","you","too");
        Set<String> set = (Set<String>) stream2.collect(Collectors.toSet());

        /**
         * 将Stream转换为Map
         */
        Stream stream3 = Stream.of("I","love","you","too");
        Map<String,Integer> map = (Map<String, Integer>) stream3.collect(Collectors.toMap(Function.identity(),String::length));

    }
}
