package com.stylefeng.guns;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.common.persistence.model.Collections;
import com.stylefeng.guns.common.persistence.model.College;
import com.stylefeng.guns.common.persistence.model.Share;
import com.stylefeng.guns.common.persistence.model.Wall1;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.system.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.modular.system.auth.security.impl.Base64SecurityAction;


/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String key = "mySecret";

        String compactJws = "******************";

        String randomKey = "****************";

        Collections edu = new Collections();
        //edu.setId((long)1);
        edu.setCollectId((long)7);
        edu.setOpenId("***************");
        //edu.setAbstracts("");
        //edu.setParentObjectId((long)0);
        //edu.setWriteContests("");
        //edu.setWriterTime("");


        String jsonString = JSON.toJSONString(edu);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + randomKey);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));

        //System.out.println("body = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody());
        //System.out.println("header = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getHeader());
        //System.out.println("signature = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getSignature());
    }
}
