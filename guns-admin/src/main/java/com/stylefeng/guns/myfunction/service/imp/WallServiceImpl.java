package com.stylefeng.guns.myfunction.service.imp;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.dao.WxuserMapper;
import com.stylefeng.guns.common.persistence.model.Wall1;
import com.stylefeng.guns.common.persistence.model.Wxuser;
import com.stylefeng.guns.myfunction.service.WallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Map;

@Service
public class WallServiceImpl implements WallService  {

    @Autowired
    WxuserMapper wxuserMapper;

    @Override
    public Map translation(Wall1 wall1)   {
        Wxuser wxuser = null;
        wxuser = wxuserMapper.selectList(new EntityWrapper<Wxuser>().eq("openId",wall1.getOpenId())).get(0);
        Map map = new HashMap();
        map.put("id",wall1.getId());
        map.put("abstracts",wall1.getAbstracts());
        map.put("writerName",wxuser.getUserName());
        map.put("writerTime",wall1.getWriterTime());
        map.put("label",wall1.getLabel());
        map.put("writeContests",wall1.getWriteContests());
        map.put("picture",wxuser.getAvatarUrl());
        map.put("parentObjectId",wall1.getParentObjectId());
        return map;
    }

    @Override
    public boolean checkWxuserIsEmpty(Wall1 wall1){
        return wxuserMapper.selectList(new EntityWrapper<Wxuser>().eq("openId",wall1.getOpenId())).isEmpty();
    }

}
