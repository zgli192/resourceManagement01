package com.wyy.ityuyaot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_user")
public class User implements Serializable {
    /**
     * 自增长主键，默认ID为1的账号为超级管理员
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 注册的昵称
     */
    private String userName;
    /**
     * 登录时的密码，不持久化到数据库
     */
    @TableField(exist = false)
    private String plainPassword;
    /**
     * 加密后的密码
     */
    private String password;
    /**
     * 用于加密的盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String iphone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户来自的平台
     */
    private String platform;
    /**
     * 用户注册时间
     */
    private String createdDate;
    /**
     * 用户最后一次登录时间
     */
    private String updatedDate;
    /**
     * 一个用户拥有多个角色
     */
    @TableField(exist = false)
    private List<Role> roleList;
    /**
     * 用户状态，0表示用户已删除
     */
    private Integer status;
    /**
     * 生日
     */
    private String birth;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * s性别
     */
    private String sex;
    /**
     * 家庭住址
     */
    private String address;
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
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
    
}