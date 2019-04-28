package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 讲座信息
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-03
 */
@TableName("sys_lecture")
public class Lecture extends Model<Lecture> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    private String leTitle;
    /**
     * 时间
     */
    private String leTime;
    /**
     * 内容
     */
    private String leCont;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeTitle() {
        return leTitle;
    }

    public void setLeTitle(String leTitle) {
        this.leTitle = leTitle;
    }

    public String getLeTime() {
        return leTime;
    }

    public void setLeTime(String leTime) {
        this.leTime = leTime;
    }

    public String getLeCont() {
        return leCont;
    }

    public void setLeCont(String leCont) {
        this.leCont = leCont;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Lecture{" +
        "id=" + id +
        ", leTitle=" + leTitle +
        ", leTime=" + leTime +
        ", leCont=" + leCont +
        "}";
    }
}
