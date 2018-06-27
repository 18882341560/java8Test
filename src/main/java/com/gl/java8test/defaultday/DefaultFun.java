package com.gl.java8test.defaultday;

/**
 * create gl  2018/6/27
 **/
public interface DefaultFun {

    default String getName(){
       return  "默认的方法";
    }

    static void show(){
        System.out.println("接口中的静态方法");
    }

}
