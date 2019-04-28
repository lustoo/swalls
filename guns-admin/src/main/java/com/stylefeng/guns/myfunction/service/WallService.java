package com.stylefeng.guns.myfunction.service;

import com.stylefeng.guns.common.persistence.model.Wall1;

import java.util.Map;

public interface WallService {

    Map translation(Wall1 wall1);

    boolean checkWxuserIsEmpty(Wall1 wall1);

}
