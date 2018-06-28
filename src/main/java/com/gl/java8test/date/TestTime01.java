package com.gl.java8test.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * create gl  2018/6/28
 *  传统解决时间方式
 **/
public class TestTime01 {


    // 存在线程安全问题
    public static void test1() throws ExecutionException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Date> task = new Callable() {
            @Override
            public Object call() throws Exception {
                return simpleDateFormat.parse("2018-06-28");
            }
        };
        List<Future<Date>> futureList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
             futureList.add(executorService.submit(task));
        }
        for (Future<Date> future : futureList) {
            System.out.println(future.get());
        }
    }

    public static void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Date> task = new Callable() {
            @Override
            public Object call() throws Exception {
                return DateFormatTheradLocal.convert("2018-06-28");
            }
        };
        List<Future<Date>> futureList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            futureList.add(executorService.submit(task));
        }
        for (Future<Date> future : futureList) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }

    // java8 新的api 线程安全的
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<LocalDate> task = new Callable() {
            @Override
            public Object call() throws Exception {
                return LocalDate.parse("2018-06-28",dateTimeFormatter);
            }
        };
        List<Future<LocalDate>> futureList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            futureList.add(executorService.submit(task));
        }
        for (Future<LocalDate> future : futureList) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }


}
