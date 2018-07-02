package com.gl.java8test.anno;

import com.gl.java8test.User;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Method;

/**
 * create gl  2018/7/2
 *   重复注解  与 类型注解
 **/
public class TestAnno {

    private @NotNull User user;

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("123") String str){

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<TestAnno> annoClass = TestAnno.class;
        Method show = annoClass.getMethod("show");
        MyAnnotation[] annotationsByType = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation mat:annotationsByType) {
            System.out.println(mat.value());
        }
    }


}
