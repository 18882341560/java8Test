package com.gl.java8test.date;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * create gl  2018/6/29
 **/
public class TestLocalDateTime {


    // LocalDate  LocalTime LocalDateTime

    public static void test(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.getYear()); // 年
        System.out.println(localDateTime.getSecond());  // 秒
        System.out.println(localDateTime.getMonthValue()); //月 中文
        System.out.println(localDateTime.getMonth());  // 月  英文
        System.out.println(localDateTime.getMinute());  // 分
        System.out.println(localDateTime.getHour());  // 小时
        System.out.println(localDateTime.getNano());  // 纳秒
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime.getDayOfWeek());  // 星期
        System.out.println(localDateTime.getDayOfMonth()); // 日


        System.out.println("-------------------------------");

        LocalDateTime localDateTime1 = LocalDateTime.of(2900,10,20,22,12,30);

        System.out.println("-------------------------------");

        // 对时间做一些操作

        // plus...  加  ; minus... 减
        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        System.out.println(localDateTime2);

    }


    //  Instant 时间戳  以Unix元年 1970年1月日 00:00:00 之间的毫秒值
    public static void test1(){
        Instant instant = Instant.now();  // 默认获取的UTC时区
        System.out.println(instant);
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));  // 偏移量
        System.out.println(offsetDateTime);  // 转为对应的毫秒值
        System.out.println(instant.toEpochMilli());  // 转为对应的毫秒值
        Instant ofEpochSecond = Instant.ofEpochSecond(1000);  // 1970年1月日 00:00:00  加上这个秒数
        System.out.println(ofEpochSecond);
    }

    /**
     *  Duration  计算两个时间之间的间隔
     *  Period     计算两个日期之间的间隔
     *
     */

    public static void test2() throws InterruptedException {

        Instant instant = Instant.now();
        Thread.sleep(1000);
        Instant instant1 = Instant.now();

        Duration between = Duration.between(instant, instant1);

        System.out.println(between.getSeconds());
        System.out.println(between.toMillis());   // to... 方法

        System.out.println("-----------------------------------");

        LocalTime cl = LocalTime.now();

        Thread.sleep(1000);

        LocalTime cl2 = LocalTime.now();

        Duration between1 = Duration.between(cl, cl2);

        System.out.println(between1.toNanos());

    }

    public static void test3(){

        LocalDate localDate = LocalDate.of(2016,10,10);
        LocalDate localDate1 = LocalDate.now();
        Period between = Period.between(localDate, localDate1);
        System.out.println(between);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
    }


    /**
     *  TemporalAdjuster   时间矫正器
     *
     */
    public static void test4(){

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime withDayOfMonth = localDateTime.withDayOfMonth(10);// with... 系列方法
        System.out.println(withDayOfMonth);

        LocalDateTime with = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));  // 下个周日是什么时候
        System.out.println(with);


        // 自定义, 下一个工作日
        LocalDateTime localDateTime1 = localDateTime.with(x -> {
            LocalDateTime ld4 = (LocalDateTime) x;   // x 参数是 Temporal
            DayOfWeek dayOfWeek = ld4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {  // 如果是周五
                return ld4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {  // 如果是周六
                return ld4.plusDays(2);
            } else {
                return ld4.plusDays(1);
            }
        });

        System.out.println(localDateTime1);

    }


    public static void main(String[] args) throws InterruptedException {
//         test();
//        test1();
//        test2();
//        test3();
        test4();
    }


}
