package com.stylefeng.guns.myfunction.datas.refresh;


import com.stylefeng.guns.common.persistence.dao.LectureMapper;
import com.stylefeng.guns.common.persistence.model.Lecture;
import com.stylefeng.guns.myfunction.datas.crawler.CrawlerLecture;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LectureRefresh {

    private static final Logger logger = LoggerFactory.getLogger(LectureRefresh.class);

    @Autowired
    LectureMapper lectureMapper;

    private CrawlerLecture lecture;

    private List<Lecture> newLectures;

    private List<Lecture> oldLectures;

    private List<Lecture> nowLectures;

    public void refresh() {
        //获取新的讲座信息

        logger.info( "讲座一览信息更新中........");

        newLectures = new ArrayList<>();

        lecture = new CrawlerLecture("http://www.usst.edu.cn/jzyl/list.htm");

        lecture.preparing();

        Map<String, Object> map = new HashMap<String, Object>();

        oldLectures = lectureMapper.selectByMap(map);
        int k = 0;
        for (Element link : lecture.getLinks()) {
            ++k;
            if (k >= 93 && k <= 106 ) {

                Lecture newLectrue = new Lecture();
                newLectrue.setLeTitle(lecture.title(link));
                newLectrue.setLeTime(lecture.time());
                newLectrue.setLeCont(lecture.contents());
                newLectures.add(newLectrue);
                //System.out.println("标题: " + eduRefresh.edu.title(link));
                //System.out.println("时间: " + eduRefresh.edu.time());
                //System.out.println("内容："+eduRefresh.edu.contents());
                //System.out.println(eduRefresh.newEdus.size());
            }
        }
        //合并新旧教务处信息
        for (int i = newLectures.size() - 1; i >= 0; i--) {
            Lecture lecture1 = newLectures.get(i);
            if (oldLectures.size() == 0) {
                lectureMapper.insert(lecture1);
            } else {
                for (int j = 0; j < oldLectures.size(); j++) {
                    if (lecture1.getLeTitle().equals(oldLectures.get(j).getLeTitle())) {
                        break;
                    } else if (j == (oldLectures.size() - 1)) {
                        lectureMapper.insert(lecture1);
                    }
                }
            }
        }
        logger.info("讲座一览信息更新完成。");
    }
}
