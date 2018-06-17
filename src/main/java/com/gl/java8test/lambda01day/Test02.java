package com.gl.java8test.lambda01day;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * create gl  2018/6/17
 * 内置的四大函数式接口
 *
 *  1. Consumer<T> : 消费型接口
 *         void accept(T t);
 *
 *  2. Supplier<T> : 供给型接口
 *         T  get();
 *
 *  3. Function<T,R> : 函数型接口
 *        R apply(T t);
 *
 *  4. Predicate<T> : 断言型接口
 *        boolean test(T t);
 *
 *        如果参数不够用，就使用这四大的子接口
 *
 **/
public class Test02 {


    public static void test1(){
        happy(1000, m -> System.out.println("大保健消费"+m));
    }

    public static void test2(){
       List<Integer> list = getNumList(10, () -> (int)(Math.random()*100));
        System.out.println(list);
    }

    public static void test3(){
       int len=strHander("adasdads", s -> s.length());
        System.out.println(len);
         String str=strHander(" fghfghsdf ", y -> y.toUpperCase().trim() );
        System.out.println(str);
    }



    // 1.Consumer<T> : 消费型接口
    public static void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }


    // 产生指定个数的整数放入集合中   2. Supplier<T> : 供给型接口
    public static List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    // Function<T,R> : 函数型接口
    public static <R> R strHander(String s, Function<String,R> fun){
        return fun.apply(s);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

}
