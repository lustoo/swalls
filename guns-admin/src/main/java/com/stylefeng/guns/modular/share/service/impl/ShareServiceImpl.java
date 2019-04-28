package com.stylefeng.guns.modular.share.service.impl;

import com.stylefeng.guns.common.persistence.model.Share;
import com.stylefeng.guns.common.persistence.dao.ShareMapper;
import com.stylefeng.guns.modular.share.service.IShareService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件共享表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-27
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

}
