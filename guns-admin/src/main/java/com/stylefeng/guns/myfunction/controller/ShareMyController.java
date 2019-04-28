package com.stylefeng.guns.myfunction.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.dao.ShareMapper;
import com.stylefeng.guns.common.persistence.model.Share;
import com.stylefeng.guns.myfunction.utils.FileToolsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 常规控制器
 *
 * @Author: YunJieJiang
 * @Date: Created in 18:21 2018/11/27 0027
 */
@Service
@Controller
@RequestMapping(value = "/auth/share")
public class ShareMyController {
        private static final Logger logger = LoggerFactory.getLogger(ShareMyController.class);

        @Autowired
        private ShareMapper shareMapper;

        public static final String ROOT = "upload-dir";

        private final ResourceLoader resourceLoader;

        @Autowired
        public ShareMyController(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }

        @PostMapping(value = "/showShare")
        public ResponseEntity giveShare() {
            List<Share> shares = shareMapper.selectList(null);
          return ResponseEntity.ok(shares);
        }

        @GetMapping("/downLoadFile/{fileName:[a-zA-Z0-9\\.\\-\\_]+}")
        public ResponseEntity downLoadPicture(@PathVariable("fileName") String fileName ,
                                              HttpServletRequest request){
            logger.info("fileName:"+fileName);
            String filePath = null;
            filePath = shareMapper.selectList(new EntityWrapper<Share>().eq("fileName",fileName)).get(0).getFilePath();
            logger.info(filePath);
            String strDirPath = request.getSession().getServletContext().getRealPath("/");
            logger.info(strDirPath);
            String pp = request.getRequestURI();
            logger.info(pp);
            String path=request.getServletContext().getContextPath();
            logger.info(path);
            String realPath=request.getServletContext().getRealPath("/static");
            logger.info(realPath);
            strDirPath = strDirPath+"WEB-INF"+ Const.FILE_SEPARATOR+"classes"+Const.FILE_SEPARATOR+"static"+Const.FILE_SEPARATOR+"upload";
            FileToolsUtil.fileToUpload(strDirPath,filePath);
            try {
                return ResponseEntity.ok(resourceLoader.getResource("file:" + strDirPath + Const.FILE_SEPARATOR + fileName));
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
        @RequestMapping("/getFileName")
        public ResponseEntity getPictureFilName(@RequestBody Share share){
        Share share1 = null;
        share1 = shareMapper.selectById(share.getId());
        if(share1.equals(null))
            return ResponseEntity.ok("NULL");
        else
            return ResponseEntity.ok(share1);
    }

}
