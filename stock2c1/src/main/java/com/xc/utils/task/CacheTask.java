package com.xc.utils.task;


import com.xc.utils.CacheUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Component
public class CacheTask {

    private final static ExecutorService EXECUTOR_SERVICE=new ThreadPoolExecutor(2,2,30, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1000));

    @Scheduled(cron = "0 0/12 9-15 ? * MON-FRI")
    public void doRemoveCacheTask(){
        EXECUTOR_SERVICE.submit(()->{
            //获取之后在删除之前的
            CacheUtil.removeExp();
        });

    }

}
