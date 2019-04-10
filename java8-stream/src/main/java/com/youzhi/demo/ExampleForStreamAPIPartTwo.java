package com.youzhi.demo;

import java.util.Optional;
import java.util.stream.Stream;

public class ExampleForStreamAPIPartTwo {
    public static void main(String[] args) {
        /**
         * stream中的两个规约操作:
         * reduce()函数
         * collect()函数
         */

        /**
         * reduce的方法定义有三种重写形式
         * Optional<T> reduce(BinaryOperator<T> accumulator)
         *      Optional是(一个)值的容器，可以避免null值的麻烦
         * T reduce(T identity,BinaryOperator<T> accumulator)
         *
         * U reduce(U identity,BiFunction<U,? super T,U> accumulator,BinaryOperator<U> combiner)
         *      BinaryOperator<U> combiner 指定并行执行时多个部分结果的合并方式
         */
        //找出最长的单词
        Stream<String> stream = Stream.of("I","love","you","too");
        Optional<String> longest = stream.reduce((s1,s2)->s1.length()>=s2.length() ? s1 : s2);
        System.out.println(longest.get());

        //求单词长度之和
        Stream<String> stream2 = Stream.of("I","love","you","too");
        Integer lengthSum = stream2.reduce(0,  //初始值 (1)
                (sum,str)->sum+str.length(),  //累加器 (2)
                (a,b)->a+b);  //部分和拼接器，并行执行时才会用到 //(3)
        System.out.println(lengthSum);


    }
}
