package com.stylefeng.guns.myfunction.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.dao.CollectionsMapper;
import com.stylefeng.guns.common.persistence.model.Collections;
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

/**
 * 常规控制器
 *
 * @author YunJieJiang
 * @date Created in 13:22 2019/1/24 0024
 */
@Service
@Controller
@RequestMapping(value = "/auth/collections")
public class CollectionsMyController {
    private static final Logger logger = LoggerFactory.getLogger(CollectionsMyController.class);

    @Autowired
    CollectionsMapper collectionsMapper;

    @PostMapping(value = "/insertCollection")
    public ResponseEntity insertCollection (@RequestBody Collections collections) {
        collectionsMapper.insert(collections);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping(value = "/deleteCollection")
    public ResponseEntity deleteCollection (@RequestBody Collections collections) {
        collectionsMapper.delete(new EntityWrapper<Collections>().eq("openId",collections.getOpenId()).eq("collectId",collections.getCollectId()));
        return ResponseEntity.ok("success");
    }
}
