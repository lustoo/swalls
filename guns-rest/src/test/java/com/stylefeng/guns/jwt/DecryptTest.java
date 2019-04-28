package com.stylefeng.guns.jwt;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.common.persistence.model.Edu;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String key = "mySecret";

        String compactJws = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJxczV4ZjciLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUwNjM0Mzk4NywiaWF0IjoxNTA1NzM5MTg3fQ.N5_npknF-w_pq_3bi-cRp0HkjQqOVlK_dTh5QPIDYcWYCujp4uQ5-QrHDB86azHhsNKVgwpvh1_0ZkxmmEFsEQ";

        String randomKey = "j6ul42";

        Edu edu = new Edu();
        edu.setId(1);
        edu.setCid(0);
        edu.setEaTitle("");
        edu.setEaTime("");
        edu.setEaCont("");


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
