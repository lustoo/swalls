package com.stylefeng.guns.myfunction.controller;

import com.stylefeng.guns.common.persistence.dao.CollegeMapper;
import com.stylefeng.guns.common.persistence.model.College;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Service
@Controller
@RequestMapping(value = "/auth/college")
public class CollegeMyController {

    private static final Logger logger = LoggerFactory.getLogger(CollegeMyController.class);

    @Autowired
    CollegeMapper collegeMapper;

    /**
     * 添加社团信息
     * @param college
     * @return
     */
    @PostMapping(value = "/get")
    public ResponseEntity getMessage (@RequestBody College college) {
        logger.info(college.toString());
        collegeMapper.insert(college);
        return ResponseEntity.ok("accept");
    }

    /**
     * 获取社团活动信息列表
     * @return
     */
    @PostMapping(value = "/all")
    public ResponseEntity allCollege(){
        List<College> college= collegeMapper.selectList(null);
        Iterator<College> itr = college.listIterator();
        College college1;
        while(itr.hasNext()){
            college1 = itr.next();
            college1.setContents("");
        }
        Collections.reverse(college);
        return ResponseEntity.ok(college);
    }

    /**
     * 获取社团活动信息具体内容
     * @param college
     * @return
     */
    @PostMapping(value = "/selectById")
    public ResponseEntity selectByIdCollege(@RequestBody College college){
        college = collegeMapper.selectById(college.getId());
        return ResponseEntity.ok(college);
    }

    /**
     * 删除社团活动信息
      * @param college
     * @return
     */
    @DeleteMapping(value = "/deleteById")
    public  ResponseEntity deleteByIdCollege(@RequestBody College college){
        logger.info("Id of DeleteCollege is:"+college.getId());
        try {
            int c = collegeMapper.deleteById(college.getId());
            if (c > 0) {
                return ResponseEntity.ok("succeed");
            }
            return ResponseEntity.ok("error");
        } catch (Exception e) {
            return ResponseEntity.ok("error");
        }
    }
}