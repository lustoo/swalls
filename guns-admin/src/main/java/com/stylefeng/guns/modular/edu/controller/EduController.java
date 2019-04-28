package com.stylefeng.guns.modular.edu.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Edu;
import com.stylefeng.guns.modular.edu.service.IEduService;

/**
 * 教务处公告管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 11:45:13
 */
@Controller
@RequestMapping("/edu")
public class EduController extends BaseController {

    private String PREFIX = "/edu/edu/";

    @Autowired
    private IEduService eduService;

    /**
     * 跳转到教务处公告管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "edu.html";
    }

    /**
     * 跳转到添加教务处公告管理
     */
    @RequestMapping("/edu_add")
    public String eduAdd() {
        return PREFIX + "edu_add.html";
    }

    /**
     * 跳转到修改教务处公告管理
     */
    @RequestMapping("/edu_update/{eduId}")
    public String eduUpdate(@PathVariable Long eduId, Model model) {
        Edu edu = eduService.selectById(eduId);
        model.addAttribute("item",edu);
        LogObjectHolder.me().set(edu);
        return PREFIX + "edu_edit.html";
    }

    /**
     * 获取教务处公告管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return eduService.selectList(null);
    }

    /**
     * 新增教务处公告管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Edu edu) {
        eduService.insert(edu);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除教务处公告管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long eduId) {
        eduService.deleteById(eduId);
        return SUCCESS_TIP;
    }

    /**
     * 修改教务处公告管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Edu edu) {
        eduService.updateById(edu);
        return super.SUCCESS_TIP;
    }

    /**
     * 教务处公告管理详情
     */
    @RequestMapping(value = "/detail/{eduId}")
    @ResponseBody
    public Object detail(@PathVariable("eduId") Long eduId) {
        return eduService.selectById(eduId);
    }
}
