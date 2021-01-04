package com.wyy.ityuyaot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 欲修改类
 *
 * @author itdragon
 */
@Data
@TableName("gm_tomo")
public class Tomo implements Serializable {
    /**
     * 自增长主键，默认ID为1的账号为超级管理员
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String userId;
    /**
     * 年休假
     */
    private String annualLeave;
    /**
     * 工种
     */
    private String craft;
    /**
     * 行政级别
     */
    private String administrative;
    /**
     * 职称级别
     */
    private String title;
    /**
     * 岗位
     */
    private String position;
    /**
     * 员工类型
     */
    private String employeeType;
    /**
     * 本单位工龄
     */
    private String seniority;
    /**
     * 薪资水平
     */
    private String wage;
    /**
     * 部门名
     */
    @TableField(exist = false)
    private String deptName;
    /**
     *
     */
    private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAnnualLeave() {
		return annualLeave;
	}
	public void setAnnualLeave(String annualLeave) {
		this.annualLeave = annualLeave;
	}
	public String getCraft() {
		return craft;
	}
	public void setCraft(String craft) {
		this.craft = craft;
	}
	public String getAdministrative() {
		return administrative;
	}
	public void setAdministrative(String administrative) {
		this.administrative = administrative;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getWage() {
		return wage;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}