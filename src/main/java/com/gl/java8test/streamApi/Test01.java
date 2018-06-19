package com.gl.java8test.streamApi;

import com.gl.java8test.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * create gl  2018/6/19
 *
 *   一。 stream的三个操作步骤
 *
 *   1. 创建流
 *   2.中间操作
 *   3.终止操作
 *
 *
 **/
public class Test01 {

    //创建流
    public static void test1(){
        //1.通过Collection 系列集合提供的stream() (串行流) 或 parallelStream() (并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stringStream =  list.stream();

        //2.通过Arrays中的静态方法 stream() 获取数组流
        User user[] = new User[10];
        Stream<User> stream = Arrays.stream(user);

        //3. 通过Stream中的静态方法 of()
        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        //4. 创建无限流
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 2); // 0 是起始值，可以改
//        stream1.forEach(System.out::println); //无限循环 加2

        stream1.limit(10)
                .forEach(System.out::println);  //只要前10个

        // 5.生成无限流
        Stream.generate( () -> Math.random())
              .limit(6)
              .forEach(System.out::println);
    }

    public static void main(String[] args) {
        test1();
    }

}
