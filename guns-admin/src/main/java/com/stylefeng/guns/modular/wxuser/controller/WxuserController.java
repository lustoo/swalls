package com.stylefeng.guns.modular.wxuser.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Wxuser;
import com.stylefeng.guns.modular.wxuser.service.IWxuserService;

/**
 * 微信用户信息管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-05 17:17:11
 */
@Controller
@RequestMapping("/wxuser")
public class WxuserController extends BaseController {

    private String PREFIX = "/wxuser/wxuser/";

    @Autowired
    private IWxuserService wxuserService;

    /**
     * 跳转到微信用户信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxuser.html";
    }

    /**
     * 跳转到添加微信用户信息管理
     */
    @RequestMapping("/wxuser_add")
    public String wxuserAdd() {
        return PREFIX + "wxuser_add.html";
    }

    /**
     * 跳转到修改微信用户信息管理
     */
    @RequestMapping("/wxuser_update/{wxuserId}")
    public String wxuserUpdate(@PathVariable Long wxuserId, Model model) {
        Wxuser wxuser = wxuserService.selectById(wxuserId);
        model.addAttribute("item",wxuser);
        LogObjectHolder.me().set(wxuser);
        return PREFIX + "wxuser_edit.html";
    }

    /**
     * 获取微信用户信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wxuserService.selectList(null);
    }

    /**
     * 新增微信用户信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Wxuser wxuser) {
        wxuserService.insert(wxuser);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除微信用户信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long wxuserId) {
        wxuserService.deleteById(wxuserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改微信用户信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Wxuser wxuser) {
        wxuserService.updateById(wxuser);
        return super.SUCCESS_TIP;
    }

    /**
     * 微信用户信息管理详情
     */
    @RequestMapping(value = "/detail/{wxuserId}")
    @ResponseBody
    public Object detail(@PathVariable("wxuserId") Long wxuserId) {
        return wxuserService.selectById(wxuserId);
    }
}
