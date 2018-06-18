package com.gl.java8test.lambda01day;

import com.gl.java8test.User;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * create gl  2018/6/18
 *
 *   一。方法引用：若lambda 体中的内容 有方法已经实现，我们可以使用”方法引用“
 *
 *    主要有三种语法格式
 *
 *    1. 对象 :: 实例方法名
 *    2. 类   :: 静态方法名
 *    3. 类   ::  实例方法名
  *
 *    注意：方法体中的参数与返回类型 必须与该函数接口中的参数返回类型保持一致
 *          第一个参数是实例的调用者，第二个参数是实例方法的参数时，可以这样写 ClassName::method
 *
 *
 *   二。 构造器引用
 *        格式： ClassName::new
 *
 *   三. 数组引用
 *       格式:  Type[]::new
 **/
public class Test03 {

    //对象 :: 实例方法名
    public static void test1(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("631235");
        System.out.println("----------------------------");
        User user=new User();
        Supplier<String> stringSupplier = user::getName;
        stringSupplier.get();
    }

    //类   :: 静态方法名
    public static void test2(){
        Comparator<Integer> comparator = Integer::compare;
    }

    //类   ::  实例方法名
    public static void test3(){
        BiPredicate biP = (x,y) -> x.equals(y);
        // 下面这样写必须是   ： 第一个参数是实例的调用者，第二个参数是实例方法的参数时，可以这样写 ClassName::method
        BiPredicate<String,String> biPredicate=String::equals;
    }

    //构造器引用   构造器的参数与返回类型 必须与该函数接口中的参数返回类型保持一致
    public static void test4(){
//        Supplier<User> userSupplier = () -> new User();
        Supplier<User> userSupplier=User::new;
        User user = userSupplier.get();


        System.out.println("----------------------------");
        Function<String,User> function = User::new;
        User user1 = function.apply("sfsdf");
        System.out.println(user1);
    }

    //数组引用
    public static void test5(){
//        Function<Integer,String[]> function = x -> new String[x];
        Function<Integer,String[]> function = String[]::new;
    }

    public static void main(String[] args) {
//        test1();
        test4();
    }
}
