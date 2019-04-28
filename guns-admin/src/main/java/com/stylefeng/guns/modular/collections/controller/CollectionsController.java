package com.stylefeng.guns.modular.collections.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Collections;
import com.stylefeng.guns.modular.collections.service.ICollectionsService;

/**
 * 问题墙收藏管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-24 13:20:28
 */
@Controller
@RequestMapping("/collections")
public class CollectionsController extends BaseController {

    private String PREFIX = "/collections/collections/";

    @Autowired
    private ICollectionsService collectionsService;

    /**
     * 跳转到问题墙收藏管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "collections.html";
    }

    /**
     * 跳转到添加问题墙收藏管理
     */
    @RequestMapping("/collections_add")
    public String collectionsAdd() {
        return PREFIX + "collections_add.html";
    }

    /**
     * 跳转到修改问题墙收藏管理
     */
    @RequestMapping("/collections_update/{collectionsId}")
    public String collectionsUpdate(@PathVariable Integer collectionsId, Model model) {
        Collections collections = collectionsService.selectById(collectionsId);
        model.addAttribute("item",collections);
        LogObjectHolder.me().set(collections);
        return PREFIX + "collections_edit.html";
    }

    /**
     * 获取问题墙收藏管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return collectionsService.selectList(null);
    }

    /**
     * 新增问题墙收藏管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Collections collections) {
        collectionsService.insert(collections);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除问题墙收藏管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer collectionsId) {
        collectionsService.deleteById(collectionsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改问题墙收藏管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Collections collections) {
        collectionsService.updateById(collections);
        return super.SUCCESS_TIP;
    }

    /**
     * 问题墙收藏管理详情
     */
    @RequestMapping(value = "/detail/{collectionsId}")
    @ResponseBody
    public Object detail(@PathVariable("collectionsId") Integer collectionsId) {
        return collectionsService.selectById(collectionsId);
    }
}
