package com.gl.java8test.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * create gl  2018/6/25
 **/
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = start - end;
        //如果到了临界值
        if(length <= THRESHOLD){
            long sum = 0;
            for (int i = 0;i <= end;i++){
                sum +=i;
            }
            return sum;
        }else {
             long middle = (start + end)/2;  //一半一半的拆分
             ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
             left.fork(); //拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
            right.fork();
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {

        // fork - join  框架,多线程执行
        Instant start = Instant.now();//开始时间

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinCalculate(0,1000000000L); // 这个值越大，耗时越小,一个亿还没有for循环快,100亿以上才快
        Long sum = forkJoinPool.invoke(forkJoinTask);
        System.out.println(sum);

        Instant end = Instant.now();//结束时间
        System.out.println(Duration.between(start,end).getSeconds()); // 秒   getNano()纳秒

    }
}
