package com.gl.java8test.streamApi;

import com.gl.java8test.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    /**
     *  归约
     *  reduce （T identity，BinaryOperation） / reduce （BinaryOperation） ----可以将流中元素反复结合起来 得到新值
     *
     */

    public static void test2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
            .reduce(0, (x,y) -> x+y ); // 先将0作为x，然后运算 0+1，再将这个结果作为x，运算 1+2，y为集合的元素，x就作为运算结果
        System.out.println(sum);

        System.out.println("----------------------------------");

        Optional<Integer> sum1 = userList.stream()
                .map(User::getAge)
                .reduce(Integer::sum);
        System.out.println(sum1.get());
    }


    /**
     *  收集
     *  collect---将流转换为其他形式，接收一个Collector的实现，用于给元素汇总的方法
     */


    public static void test3(){
        List<String> list = userList.stream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("-----------------------------");

        Set<String> set = userList.stream()
                .map(User::getName)
                .collect(Collectors.toSet());

        System.out.println("-----------------------------");

        HashSet<String> hashSet = userList.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(HashSet::new));

    }

    public static void test4(){
        //总数
       long count = userList.stream()
                .collect(Collectors.counting());

       //获取平均值
       Double age = userList.stream()
                .collect(Collectors.averagingInt(User::getAge));  //返回的都是double
        System.out.println(age);

        // 总和
       int summ = userList.stream().collect(Collectors.summingInt(User::getAge));
        System.out.println(summ);

        // 得到年龄最大的对象
       Optional<User> optionalUser = userList.stream()
                .collect(Collectors.maxBy( (u1,u2) -> Integer.compare(u1.getAge(),u2.getAge())));

      Optional<Integer> maxAge = userList.stream()
               .map(User::getAge)
               .collect(Collectors.maxBy(Integer::compare));

        System.out.println(maxAge);

        System.out.println("-----------------------------------------");

        //另外一种获取方式

        DoubleSummaryStatistics statistics = userList.stream()
                .collect(Collectors.summarizingDouble(User::getAge));
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());

    }


    /**
     *  分组
     */

    public static void test5(){
       Map<User.Status,List<User>> statusListMap = userList.stream()
                .collect(Collectors.groupingBy(User::getStatus));
        System.out.println(statusListMap);


        System.out.println("---------------------------------------");

        //多级分组

       Map<User.Status,Map<String,List<User>>> statusMapMap = userList.stream()
                .collect(Collectors.groupingBy(User::getStatus,Collectors.groupingBy( e ->{
                    if(( (User) e).getAge() <= 25){   //这里强转的形式注意下
                        return "年轻";
                    }else if( ((User) e).getAge()>25 && ((User) e).getAge()<=35){
                        return "中年";
                    }else {
                        return "老年";
                    }
                } )));
        System.out.println(statusMapMap);
    }

    /**
     *  分区
     *
     */

    public static void test6(){
       Map<Boolean,List<User>> booleanListMap = userList.stream()
                .collect(Collectors.partitioningBy( e -> e.getAge() >30));
        System.out.println(booleanListMap);
    }

    // 其他操作
    public static void test7(){
        String collect = userList.stream()
                .map(User::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);

        System.out.println("-----------------------------------");

        String collect1 = userList.stream()
                .map(User::getName)
                .collect(Collectors.joining(",","------","======"));
        System.out.println(collect1);

    }


    public static void main(String[] args) {
//        test1();
//        test2();
//        test4();
//        test5();
//        test6();
        test7();
    }


}
