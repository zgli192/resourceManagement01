package com.wyy.ityuyao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 团队实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_team")
public class Team implements Serializable {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门主管
     */
    private String departmentId;
    private String departmentName;
    /**
     * 部门职责
     */
    private String responsibility;
    /**
     * phone
     */
    private String phone;
    /**
     * 薪资
     */
    private String compensation;
    /**
     * 员工列表
     */
    @TableField(exist = false)
    private List<User> users;
    @TableField(exist = false)
    private String conten;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getConten() {
		return conten;
	}
	public void setConten(String conten) {
		this.conten = conten;
	}
    
    
}