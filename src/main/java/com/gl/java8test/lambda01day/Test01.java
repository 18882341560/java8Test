package com.gl.java8test.lambda01day;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * create gl  2018/6/17
 *  lambda 基础语法
 *
 *     ->  箭头操作符或lambda操作符
 *    左侧： lambda 表达式的参数列表
 *    右侧： lambda表达式中所需执行的功能，即lambda体
 *    需要函数式接口的支持，一个接口下只有一个方法，可以使用注解 @FunctionalInterface 修饰，可以检查是否是函数式接口
 *
 *     横批：能省则省
 *    上联：左右遇一括号省
 *    下联：左侧推断类型省
 **/
public class Test01 {


    /**
     * 无参数，无返回值
     *  () -> System.out.println("666")
     */
    public static void test1(){
       int a=0; //jdk1.8以前 必须申明为 final ，现在默认是final

       Runnable runnable=new Runnable() {
           @Override
           public void run() {
               System.out.println("666:"+a);
           }
       };
       runnable.run();
       System.out.println("---------------------------------------");

       Runnable runnable1= () -> System.out.println(777);
       runnable1.run();
    }

    /**
     * 有一个参数，无返回值
     *  (x) -> System.out.println(x)  只有一个参数 小括号可以不写
     */
    public static void test2(){
//        Consumer consumer = (x) -> System.out.println(x);
          Consumer consumer = x -> System.out.println(x);
          consumer.accept("888");
    }


    /**
     * 有多个参数，有返回值，并且lambda 体中有多个语句,需用大括号,如果只有一条语句，则 return和大括号可以不写
     * 参数列表的数据类型可以省略不写，因为jvm有类型推断
     * @return
     */
    public static void test3(){
//        Comparator<Integer> comparator = (x,y) -> {
//            System.out.println("666");
//            return Integer.compare(x,y);
//        };
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        System.out.println(comparator.compare(5,9));
    }

    /**
     * 需求：对一个数进行运算
     */
    public static void test4(){
        Integer operation = operation(100, x -> x * x);
        System.out.println(operation);
    }

    public static Integer operation(Integer num,Myfun myfun){
        return myfun.getValue(num);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

}
