package com.youzhi.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExampleForStreamAPI {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("I","love","you","too");

        //结束方法forEach()：输出所有的字符串
        stream.forEach(str -> System.out.println(str));

        //中间操作filter()：过滤stream中的对象
        stream.filter(str -> str.length() == 3).forEach(str -> System.out.println(str));

        //中间操作distinct()：去除重复元素
        stream.distinct().forEach(str -> System.out.println(str));

        //中间操作sorted()：有两个排序函数，一个是自然顺序排序，一个是自定义比较器排序
        stream.sorted((str1,str2)-> str1.length()-str2.length()).forEach(str-> System.out.println(str));

        //中间操作map()：对当前所有元素执行mapper
        stream.map(str->str.toUpperCase()).forEach(str-> System.out.println(str));

        //中间操作flatMap()：对每个元素执行mapper指定的操作，返回的所有元素组成一个新的stream
        Stream<List<Integer>> stream2 = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4,5));
        stream2.flatMap(list -> list.stream()).forEach(i -> System.out.println(i));

    }
}
