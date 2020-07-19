package com.wyy.ityuyao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 团队实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_assessment")
public class Assessment implements Serializable {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 考核人
     */
    private String userName;
    /**
     * 部门
     */
    private String userId;
    @TableField(exist = false)
    private String dept;
    @TableField(exist = false)
    private String content;
    /**
     * 月份
     */
    private String time;
    /**
     * 出勤率
     */
    private String attendance;
    /**
     * 绩效
     */
    private String performance;
    /**
     * 旷工次数
     */
    private String absenteeism;
    /**
     * 迟到次数
     */
    private String late;
    /**
     * 休假
     */
    private String vacate;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getAttendance() {
        return attendance;
    }
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
    public String getPerformance() {
        return performance;
    }
    public void setPerformance(String performance) {
        this.performance = performance;
    }
    public String getAbsenteeism() {
        return absenteeism;
    }
    public void setAbsenteeism(String absenteeism) {
        this.absenteeism = absenteeism;
    }
    public String getLate() {
        return late;
    }
    public void setLate(String late) {
        this.late = late;
    }
    public String getVacate() {
        return vacate;
    }
    public void setVacate(String vacate) {
        this.vacate = vacate;
    }


}