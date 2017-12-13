package com.mickyli.job.scheduletask.simple;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Springboot本身默认的执行方式是串行执行，也就是说无论有多少task，都是一个线程串行执行，并行需手动配置
 * @author liqian
 * @create 2017-12-12 22:44
 **/
@Component
public class SimpleTask {

    public final static long ONE_MINUTE =  60 * 1000;
    public final static long ONE_SECOND =  1000;

    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    /**
     * fixedDelay 当任务执行完毕后xx时间后在执行
     */
    @Scheduled(fixedDelay = ONE_MINUTE)
    public void fixedDelayJob(){
        System.out.println(df.format(new Date())+" >>fixedDelay执行....");
    }

    /**
     * fixedRate 每多长时间一次，不论业务执行花费了多少时间
     */
    @Scheduled(fixedRate = ONE_SECOND)
    public void fixedRateJob(){
        System.out.println(df.format(new Date())+" >>fixedRate执行....");
        Thread current = Thread.currentThread();
        System.out.println("fixedRateJob 定时任务: " + current.getId() + ", name: " + current.getName());
    }

    /**
     * initialDelay，是第一次调用前需要等待的时间，这里表示被调用后的，推迟一秒再执行
     */
    @Scheduled(initialDelay = ONE_SECOND, fixedRate = ONE_SECOND)
    public void delayFixedRateJob() {
        System.out.println(df.format(new Date())+" >>delayFixedRateJob执行....");
    }

    /**
     * corn表达式
     *
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
       另外：1表示星期天，2表示星期一。
     * 第七位，年份，可以留空，取值1970-2099
     *
     * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
     * (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。
       同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
     * (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
     * (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
     * (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60
       另：x/y，等同于 (*)/y
     *
     */
    @Scheduled(cron="*/3 * * * * ?")
    public void cronJob(){
        System.out.println(df.format(new Date())+" >>cron执行....");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread current = Thread.currentThread();
        System.out.println("cronJob 定时任务: " + current.getId() + ", name: " + current.getName());
    }
}
