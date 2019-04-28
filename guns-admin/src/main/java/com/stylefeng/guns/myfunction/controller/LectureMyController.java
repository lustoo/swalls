package com.stylefeng.guns.myfunction.controller;

import com.stylefeng.guns.common.persistence.dao.LectureMapper;
import com.stylefeng.guns.common.persistence.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
@Controller
@RequestMapping(value = "/auth/lecture")
public class LectureMyController {

    @Autowired
    LectureMapper lectureMapper;

    @PostMapping(value = "/all")
    public ResponseEntity allLecture(){
        List<Lecture> lecture= lectureMapper.selectList(null);
        Iterator<Lecture> itr = lecture.listIterator();
        Lecture lecture1;
        while(itr.hasNext()){
            lecture1 = itr.next();
            lecture1.setLeCont("");
        }
        Collections.reverse(lecture);
        return ResponseEntity.ok(lecture);
    }

    @PostMapping(value = "/selectById")
    public ResponseEntity idLecture(@RequestBody Lecture lecture){
        lecture = lectureMapper.selectById(lecture.getId());
        return ResponseEntity.ok(lecture);
    }
}
