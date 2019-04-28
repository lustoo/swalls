package com.stylefeng.guns.modular.lecture.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Lecture;
import com.stylefeng.guns.modular.lecture.service.ILectureService;

/**
 * 讲座信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-03 18:34:26
 */
@Controller
@RequestMapping("/lecture")
public class LectureController extends BaseController {

    private String PREFIX = "/lecture/lecture/";

    @Autowired
    private ILectureService lectureService;

    /**
     * 跳转到讲座信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "lecture.html";
    }

    /**
     * 跳转到添加讲座信息
     */
    @RequestMapping("/lecture_add")
    public String lectureAdd() {
        return PREFIX + "lecture_add.html";
    }

    /**
     * 跳转到修改讲座信息
     */
    @RequestMapping("/lecture_update/{lectureId}")
    public String lectureUpdate(@PathVariable Long lectureId, Model model) {
        Lecture lecture = lectureService.selectById(lectureId);
        model.addAttribute("item",lecture);
        LogObjectHolder.me().set(lecture);
        return PREFIX + "lecture_edit.html";
    }

    /**
     * 获取讲座信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return lectureService.selectList(null);
    }

    /**
     * 新增讲座信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Lecture lecture) {
        lectureService.insert(lecture);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除讲座信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long lectureId) {
        lectureService.deleteById(lectureId);
        return SUCCESS_TIP;
    }

    /**
     * 修改讲座信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Lecture lecture) {
        lectureService.updateById(lecture);
        return super.SUCCESS_TIP;
    }

    /**
     * 讲座信息详情
     */
    @RequestMapping(value = "/detail/{lectureId}")
    @ResponseBody
    public Object detail(@PathVariable("lectureId") Long lectureId) {
        return lectureService.selectById(lectureId);
    }
}
