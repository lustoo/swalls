package com.stylefeng.guns.modular.share.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Share;
import com.stylefeng.guns.modular.share.service.IShareService;

/**
 * 文件共享信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 18:20:21
 */
@Controller
@RequestMapping("/share")
public class ShareController extends BaseController {

    private String PREFIX = "/share/share/";

    @Autowired
    private IShareService shareService;

    /**
     * 跳转到文件共享信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "share.html";
    }

    /**
     * 跳转到添加文件共享信息
     */
    @RequestMapping("/share_add")
    public String shareAdd() {
        return PREFIX + "share_add.html";
    }

    /**
     * 跳转到修改文件共享信息
     */
    @RequestMapping("/share_update/{shareId}")
    public String shareUpdate(@PathVariable Long shareId, Model model) {
        Share share = shareService.selectById(shareId);
        model.addAttribute("item",share);
        LogObjectHolder.me().set(share);
        return PREFIX + "share_edit.html";
    }

    /**
     * 获取文件共享信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return shareService.selectList(null);
    }

    /**
     * 新增文件共享信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Share share) {
        shareService.insert(share);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除文件共享信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long shareId) {
        shareService.deleteById(shareId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文件共享信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Share share) {
        shareService.updateById(share);
        return super.SUCCESS_TIP;
    }

    /**
     * 文件共享信息详情
     */
    @RequestMapping(value = "/detail/{shareId}")
    @ResponseBody
    public Object detail(@PathVariable("shareId") Long shareId) {
        return shareService.selectById(shareId);
    }
}
