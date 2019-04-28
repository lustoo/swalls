package com.stylefeng.guns.modular.college.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.College;
import com.stylefeng.guns.modular.college.service.ICollegeService;

/**
 * 社团活动公告控制器
 *
 * @author fengshuonan
 * @Date 2018-11-06 14:08:16
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private String PREFIX = "/college/college/";

    @Autowired
    private ICollegeService collegeService;

    /**
     * 跳转到社团活动公告首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "college.html";
    }

    /**
     * 跳转到添加社团活动公告
     */
    @RequestMapping("/college_add")
    public String collegeAdd() {
        return PREFIX + "college_add.html";
    }

    /**
     * 跳转到修改社团活动公告
     */
    @RequestMapping("/college_update/{collegeId}")
    public String collegeUpdate(@PathVariable Long collegeId, Model model) {
        College college = collegeService.selectById(collegeId);
        model.addAttribute("item",college);
        LogObjectHolder.me().set(college);
        return PREFIX + "college_edit.html";
    }

    /**
     * 获取社团活动公告列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return collegeService.selectList(null);
    }

    /**
     * 新增社团活动公告
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(College college) {
        collegeService.insert(college);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除社团活动公告
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long collegeId) {
        collegeService.deleteById(collegeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改社团活动公告
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(College college) {
        collegeService.updateById(college);
        return super.SUCCESS_TIP;
    }

    /**
     * 社团活动公告详情
     */
    @RequestMapping(value = "/detail/{collegeId}")
    @ResponseBody
    public Object detail(@PathVariable("collegeId") Long collegeId) {
        return collegeService.selectById(collegeId);
    }
}
