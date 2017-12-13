package com.mickyli.job.scheduletask.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqian
 * @create 2017-12-13 00:42
 **/
@RestController
public class RefreshScheduleController {

    @Autowired
    DynamicScheduleTask dynamicScheduleTask;

    // 更新动态任务时间
    @RequestMapping("/updateDynamicScheduleTask")
    public void updateDynamicScheduledTask() {
        dynamicScheduleTask.setCron("0/10 * * * * ?");
    }
}
