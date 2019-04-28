package com.stylefeng.guns.myfunction.controller;

import com.stylefeng.guns.common.persistence.dao.EduMapper;
import com.stylefeng.guns.common.persistence.model.Edu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(value = "/auth/edu")
public class EduMyController {

    private static final Logger logger = LoggerFactory.getLogger(EduMyController.class);

    @Autowired
    EduMapper eduMapper;

    /**
     * 获取教务处公告列表
     * @return
     */
    @PostMapping(value = "/all")
    public ResponseEntity allEdu(){
        List<Edu> edu= eduMapper.selectList(null);
        Iterator<Edu> itr = edu.listIterator();
        Edu edu1;
        while(itr.hasNext()){
            edu1 = itr.next();
            edu1.setEaCont("");
        }
        Collections.reverse(edu);
        return ResponseEntity.ok(edu);
    }

    /**
     * 获取教务处公告具体内容
     * @param edu
     * @return
     */
    @PostMapping(value = "/selectById")
    public ResponseEntity idEdu(@RequestBody Edu edu){
        edu = eduMapper.selectById(edu.getId());
        logger.info(edu.toString());
        return ResponseEntity.ok(edu);
    }
}