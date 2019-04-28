package com.stylefeng.guns.modular.wall1.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Wall1;
import com.stylefeng.guns.modular.wall1.service.IWall1Service;

/**
 * 问题墙信息管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 10:20:55
 */
@Controller
@RequestMapping("/wall1")
public class Wall1Controller extends BaseController {

    private String PREFIX = "/wall1/wall1/";

    @Autowired
    private IWall1Service wall1Service;

    /**
     * 跳转到问题墙信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wall1.html";
    }

    /**
     * 跳转到添加问题墙信息管理
     */
    @RequestMapping("/wall1_add")
    public String wall1Add() {
        return PREFIX + "wall1_add.html";
    }

    /**
     * 跳转到修改问题墙信息管理
     */
    @RequestMapping("/wall1_update/{wall1Id}")
    public String wall1Update(@PathVariable Long wall1Id, Model model) {
        Wall1 wall1 = wall1Service.selectById(wall1Id);
        model.addAttribute("item",wall1);
        LogObjectHolder.me().set(wall1);
        return PREFIX + "wall1_edit.html";
    }

    /**
     * 获取问题墙信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wall1Service.selectList(null);
    }

    /**
     * 新增问题墙信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Wall1 wall1) {
        wall1Service.insert(wall1);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除问题墙信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long wall1Id) {
        wall1Service.deleteById(wall1Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改问题墙信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Wall1 wall1) {
        wall1Service.updateById(wall1);
        return super.SUCCESS_TIP;
    }

    /**
     * 问题墙信息管理详情
     */
    @RequestMapping(value = "/detail/{wall1Id}")
    @ResponseBody
    public Object detail(@PathVariable("wall1Id") Long wall1Id) {
        return wall1Service.selectById(wall1Id);
    }
}
