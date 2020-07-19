package com.wyy.ityuyao.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import java.io.Serializable;
/**
 * @program: ityuyao
 * @description: 团体实体类
 * @author: lizhe zeng
 * @create: 2020-07-05 13:44
 **/
@Data
@TableName("gm_apply")
public class Apply implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /*
    * 申请编号
    * */
    private String userId;
    private String userName;
    /*
    * 申请类型
    * */
    private String type;
    /*
    * 申请内容
    * */
    private String main;
    /*
    * 申请时间
    * */
    private String createdDate;
    /*
    * 申请状态
    * */
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
