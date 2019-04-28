package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 问题墙
 * </p>
 *
 * @author stylefeng123
 * @since 2018-10-29
 */
@TableName("sys_wall_1")
public class Wall1 extends Model<Wall1> {

    private static final long serialVersionUID = 1L;

    /**
     * 对象id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 微信用户id
     */
    private String openId;
    /**
     * 摘要
     */
    private String abstracts;
    /**
     * 创建时间
     */
    private String writerTime;
    /**
     * 标签
     */
    private String label;
    /**
     * 内容
     */
    private String writeContests;
    /**
     * 父对象id
     */
    private Long parentObjectId;


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

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(String writerTime) {
        this.writerTime = writerTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWriteContests() {
        return writeContests;
    }

    public void setWriteContests(String writeContests) {
        this.writeContests = writeContests;
    }

    public Long getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(Long parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Wall1{" +
        "id=" + id +
        ", openId=" + openId +
        ", abstracts=" + abstracts +
        ", writerTime=" + writerTime +
        ", label=" + label +
        ", writeContests=" + writeContests +
        ", parentObjectId=" + parentObjectId +
        "}";
    }
}
