package com.gl.java8test.streamApi;

import com.gl.java8test.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * create gl  2018/6/20
 **/
public class Test03 {

    /**
     * 终止操作
     *
     *  查找与匹配
     *  allMatch --检查是否匹配所有元素
     *  anyMatch --检查是否至少匹配一个元素
     *  noneMatch --检查是否没有匹配所有元素
     *  findFirst -- 返回第一个元素
     *  findAny -- 返回流中的任意一个元素
     *  count -- 返回流中的总个数
     *  max -- 返回流中最大值
     *  min -- 返回流中最小值
     *
     */

    public static List<User> userList = Arrays.asList(
            new User("gl",25,"男",User.Status.FREE),
            new User("张三",35,"男",User.Status.BUSY),
            new User("美女",22,"女",User.Status.VOCATION),
            new User("李四",48,"男",User.Status.FREE),
            new User("王五",19,"男",User.Status.BUSY),
            new User("王五",19,"男",User.Status.VOCATION),
            new User("王五",19,"男",User.Status.FREE)
    );

    public static void test1(){
        // 检查是否全部是空闲
        boolean bool= userList.stream()
                 .allMatch( user -> user.getStatus().equals(User.Status.FREE));
        System.out.println(bool);

        System.out.println("---------------------------");

        // 检查是否至少匹配一个元素
        boolean bool2= userList.stream()
                .anyMatch( user -> user.getStatus().equals(User.Status.FREE));
        System.out.println(bool2);

        System.out.println("-----------------------------");

        boolean bool3= userList.stream()
                .noneMatch( user -> user.getStatus().equals(User.Status.FREE));
        System.out.println(bool3);

        System.out.println("-------------------------------");

       Optional<User> optionalUser = userList.stream()      //串行流
                .sorted( (u1,u2) -> -Integer.compare(u1.getAge(),u2.getAge()))
                .findFirst();
        System.out.println(optionalUser.get());

        System.out.println("---------------------------------");
       Optional<User> optionalUser1 = userList.parallelStream()    //并行流
                .filter( e -> e.getStatus().equals(User.Status.FREE))
                .findAny();
        System.out.println(optionalUser1.get());

        System.out.println("---------------------------------");

        long lo = userList.stream()
                .count();
        System.out.println(lo);

        System.out.println("---------------------------------");

       Optional<Integer> minAge = userList.stream()
                .map(User::getAge)
                .min(Integer::compareTo);

        System.out.println(minAge.get());
    }


    public static void main(String[] args) {
        test1();
    }


}
