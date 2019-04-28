package com.stylefeng.guns.modular.wallpicture.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.WallPicture;
import com.stylefeng.guns.modular.wallpicture.service.IWallPictureService;

/**
 * 问题墙图片管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 10:21:59
 */
@Controller
@RequestMapping("/wallPicture")
public class WallPictureController extends BaseController {

    private String PREFIX = "/wallpicture/wallPicture/";

    @Autowired
    private IWallPictureService wallPictureService;

    /**
     * 跳转到问题墙图片管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wallPicture.html";
    }

    /**
     * 跳转到添加问题墙图片管理
     */
    @RequestMapping("/wallPicture_add")
    public String wallPictureAdd() {
        return PREFIX + "wallPicture_add.html";
    }

    /**
     * 跳转到修改问题墙图片管理
     */
    @RequestMapping("/wallPicture_update/{wallPictureId}")
    public String wallPictureUpdate(@PathVariable Long wallPictureId, Model model) {
        WallPicture wallPicture = wallPictureService.selectById(wallPictureId);
        model.addAttribute("item",wallPicture);
        LogObjectHolder.me().set(wallPicture);
        return PREFIX + "wallPicture_edit.html";
    }

    /**
     * 获取问题墙图片管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wallPictureService.selectList(null);
    }

    /**
     * 新增问题墙图片管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WallPicture wallPicture) {
        wallPictureService.insert(wallPicture);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除问题墙图片管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long wallPictureId) {
        wallPictureService.deleteById(wallPictureId);
        return SUCCESS_TIP;
    }

    /**
     * 修改问题墙图片管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WallPicture wallPicture) {
        wallPictureService.updateById(wallPicture);
        return super.SUCCESS_TIP;
    }

    /**
     * 问题墙图片管理详情
     */
    @RequestMapping(value = "/detail/{wallPictureId}")
    @ResponseBody
    public Object detail(@PathVariable("wallPictureId") Long wallPictureId) {
        return wallPictureService.selectById(wallPictureId);
    }
}
