package com.gl.java8test.optional;

import com.gl.java8test.User;

import java.util.Optional;

/**
 * create gl  2018/6/26
 **/
public class TestOptional {

    /**
     *   optional 常用方法：
     *   Optional.of( T t) ----创建一个optional 实例
     *   Optional.Empty() ----- 创建一个空的optional 实例
     *   Optional.ofNullable( T t)  ---- 若t不为空，创建optional实例，否则创建空实例
     *   isPresent()  ------判断是否包含值
     *   orElse( T t) ------ 如果调用对象包含值，返回改值，否则返回 t
     *   orElseGet ( Supplier s) ----- 如果调用对象包含值，返回该值，否则返回 s 获取的值
     *   map( Function f) -----如果有值对其处理，并 返回处理后的optional，否则返回 Optional.Empty()
     *   flatMap ( Function mapper)  ---- 与map 类似，要求返回值必须是Optional
     *
     */


    public static void test1(){
        Optional<User> optionalUser = Optional.of(new User()); //如果传入的是 null ，就会报空指针
        System.out.println(optionalUser.get());
    }

    public static void test2(){
        Optional<User> empty = Optional.empty();
        System.out.println(empty.get());   // 空的Optional get不到值
    }

    public static void test3(){
        Optional<User> optionalUser = Optional.ofNullable(new User()); //如果传入的是 null ，不会报错,而是get不到值
        if(optionalUser.isPresent()){
            System.out.println(optionalUser.get());
        }
        User user = optionalUser.orElse(new User("gl", 25, "男")); // 如果有值，返回，没值，返回我们创建的 new User("gl", 25, "男")
        System.out.println(user);

        User orElseGet = optionalUser.orElseGet(() -> new User());
        System.out.println(orElseGet);
    }

    public static void test4(){
        Optional<User> optionalUser = Optional.ofNullable(new User());
        Optional<String> optionalS = optionalUser.map(e -> e.getName());
        System.out.println(optionalS.get());

        Optional<String> s = optionalUser.flatMap(e -> Optional.of(e.getName())); // 返回值必须是Optional
        System.out.println(s.get());
    }

    /**
     *  获取一个男人心中女神的名字
     */
    public static void test5(){
       Man man = new Man();
       String name = getName(man);
       System.out.println(name);
    }

    // 以前的解决空指针异常
    public static String getName(Man man){
        if(man != null){
           Godness godness = man.getGodness();
           if(godness != null){
               return godness.getName();
           }
        }
        return "昌老师";
    }

    // 这样就避免了空指针异常
    public static void test6(){
//        Optional<Godness> godness = Optional.ofNullable(null);
        Optional<Godness> godness = Optional.ofNullable(new Godness("波老师"));
//        Optional<NewMan> newMan = Optional.ofNullable(null);
        Optional<NewMan> newMan = Optional.ofNullable(new NewMan(godness));
        System.out.println(getNewName(newMan));
    }

    // 改造 得到女神的名字
    public static String getNewName(Optional<NewMan> man){
       return man.orElse(new NewMan())
           .getGodness()
           .orElse(new Godness("昌老师"))
           .getName();
    }

    public static void main(String[] args) {
//       test3();
//        test5();
        test6();
    }




}
