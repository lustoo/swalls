package com.stylefeng.guns.modular.lecture.service.impl;

import com.stylefeng.guns.common.persistence.model.Lecture;
import com.stylefeng.guns.common.persistence.dao.LectureMapper;
import com.stylefeng.guns.modular.lecture.service.ILectureService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲座信息 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-03
 */
@Service
public class LectureServiceImpl extends ServiceImpl<LectureMapper, Lecture> implements ILectureService {

}
