package com.stylefeng.guns.myfunction.schedule;

import com.stylefeng.guns.myfunction.controller.WallMyController;
import com.stylefeng.guns.myfunction.datas.refresh.EduRefresh;
import com.stylefeng.guns.myfunction.datas.refresh.LectureRefresh;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 定时任务 控制器
 *   1、教务处公告信息刷新
 *
 * @Author: YunJieJiang
 * @Date: Created in 11:20 2018/11/1 0001
 */
@Component
public class Schedule {
    private static final Logger logger = LoggerFactory.getLogger(WallMyController.class);

    private static final long initialDelay = 20*1000;

    private static final long eduWaitTime  = 21600*1000;

    private static final long lectureWaitTime  = 21600*1000;

    @Autowired
    EduRefresh eduRefresh;

    @Autowired
    LectureRefresh lectureRefresh;

    private static String eduInformation = "定时任务：教务处公告信息更新 间隔时长：" + eduWaitTime/1000 + "s";

    private static String lectureInformation = "定时任务：讲座一览信息更新 间隔时长：" + lectureWaitTime/1000 + "s";

    @Scheduled(initialDelay = initialDelay,fixedRate = eduWaitTime)
    public void eduRefresh(){
        logger.info(eduInformation);
        try{
            eduRefresh.refresh();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Scheduled(initialDelay = initialDelay,fixedRate = lectureWaitTime)
    public void LectureRefresh(){
        logger.info(lectureInformation);
        try{
            lectureRefresh.refresh();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
