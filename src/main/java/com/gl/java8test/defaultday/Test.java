package com.gl.java8test.defaultday;

/**
 * create gl  2018/6/27
 **/
public class Test extends MyFun implements DefaultFun,DefaultFun1{

    public static void main(String[] args) {
        // 默认方法的  '类优先'原则：
        /**
         * 如果一个接口定义了一个默认方法，而另一个父类或接口中定义了一个同名的方法时。
         *  1. 选择父类的方法 ：如果父类提供了具体实现，那么接口中的默认方法会被忽略
         */
        Test test =new Test();
        String name = test.getName();
        System.out.println(name);

        Test2 test2 =new Test2();
        System.out.println(test2.getName());

        System.out.println("------------------");
        DefaultFun.show();
    }

}

class Test2 implements DefaultFun,DefaultFun1{


//    @Override
//    public String getName() {
//        return "666";
//    }

    // 也可以这样
    @Override
    public String getName() {
        return DefaultFun1.super.getName();
    }
}
