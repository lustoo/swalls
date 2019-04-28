package com.stylefeng.guns.myfunction.utils;

import java.io.*;

/**
 *  序列化的方式来实现对象的深拷贝
 * @Author: YunJieJiang
 * @Date: Created in 13:42 2019/1/24 0024
 */
public class CloneUtils {

	    @SuppressWarnings("unchecked")
	    public static <T extends Serializable> T clone(T obj){
	        T clonedObj = null;
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                oos.close();
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                clonedObj = (T) ois.readObject();
                ois.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return clonedObj;
	    }
}