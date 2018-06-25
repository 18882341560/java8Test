package com.gl.java8test.streamApi;

import java.util.stream.LongStream;

/**
 * create gl  2018/6/25
 **/
public class Test04 {



    public static void test1(){
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
    }


    public static void main(String[] args) {
        test1();
    }


}
