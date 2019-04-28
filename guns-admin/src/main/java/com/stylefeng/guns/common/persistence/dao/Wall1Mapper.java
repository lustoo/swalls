package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Wall1;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 问题墙 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-10-29
 */
@Component
public interface Wall1Mapper extends BaseMapper<Wall1> {
    @Select("select * from sys_wall_1 where sys_wall_1.abstracts like CONCAT('%',#{0},'%')")
    List<Wall1> selectByAbstracts(String abstracts);
}
