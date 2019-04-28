package com.stylefeng.guns.myfunction.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.dao.CollectionsMapper;
import com.stylefeng.guns.common.persistence.dao.Wall1Mapper;
import com.stylefeng.guns.common.persistence.dao.WallPictureMapper;
import com.stylefeng.guns.common.persistence.model.Wall1;
import com.stylefeng.guns.common.persistence.model.WallPicture;
import com.stylefeng.guns.modular.system.auth.converter.MultipartHttpConverter;
import com.stylefeng.guns.myfunction.service.WallService;
import com.stylefeng.guns.myfunction.utils.CloneUtils;
import com.stylefeng.guns.myfunction.utils.FileToolsUtil;
import com.stylefeng.guns.myfunction.utils.PictureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Service
@Controller
@RequestMapping(value = "/auth/wall")
public class WallMyController {
    private static final Logger logger = LoggerFactory.getLogger(WallMyController.class);
    /**
     * 问题集合
     */
    private HashMap<Long, Map> questions = new HashMap<>();
    /**
     *回答集合
     */
    private HashMap<Long, List<Map>> answers = new HashMap<>();

    private List<Wall1> wall;

    public static final String ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    Wall1Mapper wall1mapper;

    @Autowired
    WallService wallService;

    @Autowired
    WallPictureMapper wallPictureMapper;

    @Autowired
    CollectionsMapper collectionsMapper;

    @Autowired
    public WallMyController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping(value = "/get")//接受信息存入
    public ResponseEntity getMessage (@RequestBody Wall1 walll) {
        logger.info(walll.toString());
        if(wallService.checkWxuserIsEmpty(walll))
            return  ResponseEntity.ok("No such Wxuser");
        wall1mapper.insert(walll);
        if(walll.getParentObjectId().equals((long)0)){
            Map map = wallService.translation(walll);
            map.put("writeContests","");
            questions.put(walll.getId(),map);
        }
        else{
            if(!answers.containsKey(walll.getParentObjectId()))
                answers.put(walll.getParentObjectId(),new ArrayList<Map>());
            Map map = wallService.translation(walll);
            answers.get(walll.getParentObjectId()).add(0,map);
        }
        return ResponseEntity.ok("accept");
    }

    @PostMapping(value = "/selectByAbstracts")
    public ResponseEntity giveByAbstracts(@RequestBody Wall1 wall1) {
        //获取用户收藏列表
        Set<Long> set = new HashSet<>();
        List<com.stylefeng.guns.common.persistence.model.Collections> collections = collectionsMapper.selectList(new EntityWrapper<com.stylefeng.guns.common.persistence.model.Collections>().eq("openId",wall1.getOpenId()));
        for(com.stylefeng.guns.common.persistence.model.Collections collection:collections)
            set.add(collection.getCollectId());

        if(wall1.getAbstracts() == null){
            List<Map> wall1s = new ArrayList<Map>(CloneUtils.clone(this.questions).values());
            for(Map map : wall1s){
                if(set.contains(map.get("id")))
                    map.put("isCollection",1);
                else
                    map.put("isCollection",0);
            }
            return ResponseEntity.ok(wall1s);
        }
        else{
            wall = wall1mapper.selectByAbstracts(wall1.getAbstracts());
            Collections.reverse(wall);
            List<Map> maps = new ArrayList<>();
            for(Wall1 temp : wall){
                Map map =wallService.translation(temp);
                map.put("writeContests","");
                if(set.contains(map.get("id")))
                    map.put("isCollection",1);
                else
                    map.put("isCollection",0);
                maps.add(map);
            }
            return ResponseEntity.ok(maps);
        }
    }

    @PostMapping(value = "/getCollectQuestions")
    public ResponseEntity insertCollection (@RequestBody Wall1 wall1) {
        List<Map> maps = new ArrayList<>();
        //获取用户收藏列表
        List<com.stylefeng.guns.common.persistence.model.Collections> collections = collectionsMapper.selectList(new EntityWrapper<com.stylefeng.guns.common.persistence.model.Collections>().eq("openId",wall1.getOpenId()));
        for(com.stylefeng.guns.common.persistence.model.Collections collection:collections)
            maps.add(questions.get(collection.getCollectId()));
        return ResponseEntity.ok(maps);
    }

    @PostMapping(value = "/showQuestion")
    public ResponseEntity giveQuestion(@RequestBody Wall1 wall1) {
        Map wall1s = questions.get(wall1.getId());
        wall1s.put("writeContests",wall1mapper.selectById(wall1.getId()).getWriteContests());
        return ResponseEntity.ok(wall1s);
    }

    @PostMapping(value = "/showAnswers")
    public ResponseEntity giveAnswers(@RequestBody Wall1 wall1) {
        List<Map> thisWallAnswers = answers.get(wall1.getId());
        return ResponseEntity.ok(thisWallAnswers);
    }

    @PostMapping(value = "/myQuestions")
    public ResponseEntity giveYourQuestions(@RequestBody Wall1 wall1) {
        List<Map> yourQuestions = new ArrayList<>();
        String openId = wall1.getOpenId();
        wall = wall1mapper.selectList(new EntityWrapper<Wall1>().eq("openId",openId)
                .eq("parentObjectId",(long)0));
        Collections.reverse(wall);
        for(Wall1 temp : wall){
            Map map =wallService.translation(temp);
            yourQuestions.add(map);
        }
        return ResponseEntity.ok(yourQuestions);
    }

    @DeleteMapping(value = "/deleteQuestion")
    public  ResponseEntity deleteYourQuestions(@RequestBody Wall1 wall1){
        logger.info("Id of DeleteCollege is:"+wall1.getId());
        try {
            int a = wall1mapper.deleteById(wall1.getId());
            int b = wall1mapper.delete(new EntityWrapper<Wall1>().eq("parentObjectId",wall1.getId()));
            List<WallPicture> wallPictures = null;
            wallPictures =  wallPictureMapper.selectList(new EntityWrapper<WallPicture>().eq("parentObjectId",wall1.getId()));
            if(!wallPictures.isEmpty()&&FileToolsUtil.deleteFile(wallPictures.get(0).getFilePath()))
                logger.info("图片删除成功！");
            int c = wallPictureMapper.delete(new EntityWrapper<WallPicture>().eq("parentObjectId",wall1.getId()));
            int d = collectionsMapper.delete(new EntityWrapper<com.stylefeng.guns.common.persistence.model.Collections>().eq("collectId",wall1.getId()));
            logger.info("问题的删除数量："+a+"该问题的评论的删除数量："+b+"该问题的图片的删除数量："+c+"该问题的收藏的删除数量："+d);
            questions.remove(wall1.getId());
            answers.remove(wall1.getId());
            //System.out.println(questions);
            //System.out.println(answers);
            if (a > 0) {
                return ResponseEntity.ok("succeed");
            }
            return ResponseEntity.ok("error");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok("error");
        }
    }

    @RequestMapping("/upLoadPicture")
    public ResponseEntity upLoadPicture(@RequestParam(value = "object")String object,
                                                 @RequestParam(value = "sign")String sign,
                                                 MultipartHttpServletRequest request)throws Exception{
        PictureData pictureData = (PictureData)new MultipartHttpConverter().read(object,sign,PictureData.class);
        wall = null;
        wall = wall1mapper.selectList(new EntityWrapper<Wall1>().
                eq("abstracts",pictureData.getAbstracts()).
                eq("writeContests",pictureData.getWriteContests()));
        if(wall.isEmpty())
            return ResponseEntity.ok("The problem does not exist");
        String file_path = Const.FILE_PATH+Const.FILE_SEPARATOR+Const.IMAGE_WALL+ Const.FILE_SEPARATOR+ Calendar.getInstance().get(Calendar.YEAR);
        String file_path_full  = FileToolsUtil.fileUpload(request.getFile("first_image"),FileToolsUtil.createDiretory(file_path));
        WallPicture wallPicture = new WallPicture();
        wallPicture.setFilePath(file_path_full);
        wallPicture.setFileName(file_path_full.substring(file_path_full.lastIndexOf(Const.FILE_SEPARATOR)+1));
        wallPicture.setParentObjectId(wall.get(0).getId());
        logger.info("FileName: " + wallPicture.getFileName());
        logger.info("FilePath: " + wallPicture.getFilePath());
        logger.info("Abstracts: " + wallPicture.getParentObjectId());
        return ResponseEntity.ok(wallPictureMapper.insert(wallPicture));
    }

    @GetMapping("/downLoadPicture/{fileName:[a-zA-Z0-9\\.\\-\\_]+}")
    public ResponseEntity downLoadPicture(@PathVariable("fileName") String fileName ,
                                          HttpServletRequest request){
        String filePath = null;
        filePath = wallPictureMapper.selectList(new EntityWrapper<WallPicture>().eq("fileName",fileName)).get(0).getFilePath();
        logger.info(filePath);
        String strDirPath = request.getSession().getServletContext().getRealPath("/");
        logger.info(strDirPath);
        String pp = request.getRequestURI();
        logger.info(pp);
        String path=request.getServletContext().getContextPath();
        logger.info(path);
        String realPath=request.getServletContext().getRealPath("/static");
        logger.info(realPath);
        strDirPath = strDirPath+"WEB-INF"+Const.FILE_SEPARATOR+"classes"+Const.FILE_SEPARATOR+"static"+Const.FILE_SEPARATOR+"upload";
        FileToolsUtil.fileToUpload(strDirPath,filePath);
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + strDirPath + Const.FILE_SEPARATOR + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/getPictureFileName")
    public ResponseEntity getPictureFilName(@RequestBody Wall1 wall1){
        List<WallPicture> wallPictures = null;
        wallPictures = wallPictureMapper.selectList(
                new EntityWrapper<WallPicture>().
                        eq("parentObjectId",wall1.getId()));
        if(wallPictures.isEmpty())
            return ResponseEntity.ok("NULL");
        else
            return ResponseEntity.ok(wallPictures.get(0).getFileName());
    }

    @PostConstruct
    public void initMethod(){
        wall = wall1mapper.selectList(null);
        for(Wall1 attribute : wall) {
            if(attribute.getParentObjectId().equals((long)0)){
                Map map = wallService.translation(attribute);
                map.put("writeContests","");
                questions.put(attribute.getId(),map);
            }
            else{
                if(!answers.containsKey(attribute.getParentObjectId()))
                    answers.put(attribute.getParentObjectId(),new ArrayList<Map>());
                Map map = wallService.translation(attribute);
                answers.get(attribute.getParentObjectId()).add(0,map);
            }
        }
    }

}