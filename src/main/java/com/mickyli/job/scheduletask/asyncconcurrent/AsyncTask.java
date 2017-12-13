package com.mickyli.job.scheduletask.asyncconcurrent;

import org.springframework.scheduling.annotation.Async;

/**
 * @author liqian
 * @create 2017-12-13 00:12
 **/
public class AsyncTask {

    /**
     * 异步方法不能内部调用，只能外部调用，否则就会变成阻塞主线程的同步任务啦
     */
    public void fakeAsyncTaskTest(){
        doSomeHeavyBackgroundTask(4000);
        printLog();
        //你会发现，当你像这样内部调用的时候，居然是同步执行的，不是异步的！！
    }

    @Async
    public void doSomeHeavyBackgroundTask(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printLog() {
        System.out.println(" i print a log ");
    }
}
