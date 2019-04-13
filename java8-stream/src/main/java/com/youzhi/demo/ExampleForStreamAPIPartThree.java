package com.youzhi.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
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
         * 知识点3：
         * collect方法
         * (1)作用：将Stream转化为一个容器(或者map)
         * (2)用法举例：
         *      collect(ArrayList::new,     //目标容器 ArrayList
         *              ArrayList::add,     //元素如何添加到容器中 ArrayList.add()
         *              ArrayList::addAll   //多个部分结果如何合并 ArrayList.addAll()
         *             )
         * (3)Collector(收集器)
         *      Collector是对上述三个参数的封装;
         *      Collectors工具类可以通过静态方法生成各种常用的Collector;
         *      </>
         */

        /**
         * 1.将Stream转换为List
         */
        Stream stream = Stream.of("I","love","you","too");
        /*List<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);*/ //方式1
        List<String> list = (List<String>) stream.collect(Collectors.toList()); //方式2
        System.out.println(list);

        /**
         * 2.将Stream转换为Set
         * 还可以使用 Collectors.toCollection(Supplier<C> collectionFactory) 指定容器的实际类型
         * HashSet<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new))
         */
        Stream stream2 = Stream.of("I","love","you","too");
        Set<String> set = (Set<String>) stream2.collect(Collectors.toSet());
        System.out.println(set);
        /**
         * 3.将Stream转换为Map
         * (1)用Collectors.toMap()生成的收集器，用户需要指定如何生成Map的key和value
         */
        Stream stream3 = Stream.of("I","love","you","too");
        Map<String,Integer> map = (Map<String, Integer>) stream3.collect(Collectors.toMap(Function.identity(),String::length));
        System.out.println(map);

        /**
         * (2)用Collectors.partitioningBy生成的收集器，适用于将stream中的元素根据某个二值逻辑，分成两个部分；
         * 比如 男女性别，成绩及格与否等等
         *
         */
        List<Student> students = new ArrayList();
        students.add(new Student("A",61));
        students.add(new Student("B",59));
        students.add(new Student("C",59));
        Map<Boolean,List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.grade >= 60));
        System.out.println(passingFailing);

        /**
         * (3)用groupingby生成的收集器，按照某个属性对数据进行分组，属性相同的元素会被对应到Map的同一个key上
         */
        Map<Integer,List<Student>> byGrade = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        System.out.println(byGrade);

        /**
         * 4.上游收集器，下游收集器
         */
        //统计各个分数的学生的人数
        Map<Integer,Long> totalNum = students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.counting()));
        System.out.println(totalNum);

        //想得到分数为59的各个学生的名称
        Map<Integer,List<String>> result = students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.mapping(Student::getName,  //下游收集器
                        Collectors.toList())));   //更下游的收集器
        System.out.println(result);


        /**
         * 5.使用collect()做字符串join
         */
        Stream<String> stream4 = Stream.of("I","love","you");
        /*String joined = stream4.collect(Collectors.joining());*/  //"Iloveyou"
        /*String joined = stream4.collect(Collectors.joining(","));*/  //"I,love,you"
        String joined = stream4.collect(Collectors.joining(",","{","}")); //"{I,love,you}"
        System.out.println(joined);

    }

    static class Student{
        public String name;
        public int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public int getGrade() {
            return grade;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }
}
