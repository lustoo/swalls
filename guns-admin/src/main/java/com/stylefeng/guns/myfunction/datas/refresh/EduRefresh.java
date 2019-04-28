package com.stylefeng.guns.myfunction.datas.refresh;

import com.stylefeng.guns.common.persistence.dao.EduMapper;
import com.stylefeng.guns.myfunction.datas.crawler.CrawlerEdu;
import com.stylefeng.guns.common.persistence.model.Edu;
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
public class EduRefresh {

    private static final Logger logger = LoggerFactory.getLogger(EduRefresh.class);

    @Autowired
    EduMapper eduMapper;

    private CrawlerEdu edu;

    private List<Edu> newEdus;

    private List<Edu> oldEdus;

    private List<Edu> nowEdus;

    public  void refresh(){

        //获取新的教务处信息
        logger.info( "教务处公告信息更新中........");

        newEdus = new ArrayList<>();
        int sun = 0;
        edu = new CrawlerEdu("http://jwc.usst.edu.cn");
        edu.preparing();
        Map<String,Object> map = new HashMap<String,Object>();
        oldEdus =  eduMapper.selectByMap(map);
        int k = 0;
        for (Element link :  edu.getLinks()) {
            if (k>=19&&k<=33 && edu.title(link) != null && edu.time() != null && edu.contents() != null){
                Edu newEdu = new Edu();
                newEdu.setEaTitle(edu.title(link));
                newEdu.setEaTime(edu.time());
                newEdu.setEaCont(edu.contents());
                newEdus.add(newEdu);
            }
            k++;
        }

        //合并新旧教务处信息
        for(int i = newEdus.size()-1; i >= 0; i--){
            Edu edu1 = newEdus.get(i);
            if(oldEdus.size() == 0){
                eduMapper.insert(edu1);
            }else{
                for(int j = 0; j < oldEdus.size(); j++){
                    if(edu1.getEaTitle().equals(oldEdus.get(j).getEaTitle())){
                        break;
                    }
                    else if(j == (oldEdus.size()-1)){
                        eduMapper.insert(edu1);
                    }
                }
            }
        }

        logger.info("教务处公告信息更新完成。");
        /**
         //刷新排序
         eduRefresh.nowEdus = eduRefresh.eduMapper.selectByMap(map);
         for(int i = eduRefresh.nowEdus.size()-1,j = 1;i >= 0 ; i--,j++){
         eduRefresh.nowEdus.get(i).setCid(j);
         eduRefresh.eduMapper.updateAllColumnById(eduRefresh.nowEdus.get(i));
         }*/
    }
}
