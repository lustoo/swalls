package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 微信用户
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-05
 */
@TableName("sys_wxuser")
public class Wxuser extends Model<Wxuser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * openid
     */
    private String openId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 头像？
     */
    private String avatarUrl;
    /**
     * 姓名
     */
    private String stuName;
    /**
     * 学号
     */
    private String stuNum;
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 会话密钥
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 开放平台标识符
     */
    private String unionid;
    /**
     * 用户限权
     */
    private Integer power;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Wxuser{" +
        "id=" + id +
        ", openId=" + openId +
        ", userName=" + userName +
        ", gender=" + gender +
        ", avatarUrl=" + avatarUrl +
        ", stuName=" + stuName +
        ", stuNum=" + stuNum +
        ", country=" + country +
        ", province=" + province +
        ", city=" + city +
        ", sessionKey=" + sessionKey +
        ", unionid=" + unionid +
        ", power=" + power +
        "}";
    }
}
