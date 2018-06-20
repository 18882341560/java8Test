package com.gl.java8test.streamApi;

import com.gl.java8test.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author green
 * @date 2018/6/20/020
 */
public class Test02 {

   public static List<User> userList = Arrays.asList(
            new User("gl",25,"男"),
            new User("张三",35,"男"),
            new User("美女",22,"女"),
            new User("李四",48,"男"),
            new User("王五",19,"男"),
            new User("王五",19,"男"),
            new User("王五",19,"男")
    );

   //中间操作
    /**
     *  筛选与切片
     *
     *   filter -- 接受 lambda,从流中排除某些元素
     *   limit  -- 截断流 ，使其元素不超过指定数量
     *   skip(n) -- 跳过元素 ，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit (n) 互补
     *   distinct -- 筛选 ，通过流所生成的hashcode() 和equals() 去除重复元素
     */
    public static void test1(){
//        userList.stream()
//                .filter( u -> u.getAge() > 30)   //传入一个 断言型操作
//                .forEach(System.out::println);   //终止操作

        // 内部迭代：迭代有streamApi完成
        userList.stream()
                .filter( u -> {
                    System.out.println("过滤操作");
                    return u.getAge() > 30;
                }).forEach(System.out::println); //没有终止操作，中间操作根本不会执行,称为惰性求值
    }

    // limit 用法
    public static void test2(){
        userList.stream()
                .filter( u -> u.getAge() > 20)
                .limit(2)
                .forEach(System.out::println);
    }

    // 短路操作，满足条件后，就不在执行了
    public static void test3(){
        userList.stream()
                .filter( u -> {
                    System.out.println("短路");
                    return u.getAge() >20;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    // 跳过满足条件的前两个
    public static void test4(){
        userList.stream()
                .filter( u -> u.getAge() >20)
                .skip(2)
                .forEach(System.out::println);
    }

    // 去重
    public static void test5(){
        userList.stream()
                .filter( u -> u.getAge() >20)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     *  映射
     *
     *  map -- 接受lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，该函数会被用到每个元素上，
     *  并将其映射成一个新的元素。
     *
     *  flatMap --接受一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流
     *
     */

    public static void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
        list.stream()
            .map( s -> s.toUpperCase())   //转大写
            .forEach(System.out::println);

        System.out.println("----------------------------");

        userList.stream()
                .map( User::getName)  //得到姓名 并去重
                .distinct()
                .forEach(System.out::println);

        System.out.println("----------------------------");

//        list.stream()
//            .map( Test02::filterString)
//            .forEach(
//                    sm -> {
//                        sm.forEach(System.out::println);
//                    }
//            );

        //这样写很麻烦
//       Stream<Stream<Character>> streamStream = list.stream()
//            .map( Test02::filterString);  //{ { a,a,a },{ b,b,b} }  大括号代表流  相当于 集合中的 add(List) 这样的操作
//
//        streamStream.forEach(
//                sm -> {
//                    sm.forEach(System.out::println);
//                }
//        );

       Stream<Character> characterStream = list.stream()
            .flatMap(Test02::filterString);   //{  a,a,a ,b,b,b } 类似这样的效果,相当于 addAll(List)  这样的操作

        characterStream.forEach(System.out::println);


    }

    //将字符的每个元素添加到集合
    public static Stream<Character> filterString(String str){
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }



    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }


}
