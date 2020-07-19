package com.wyy.ityuyao.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Apply;
import com.wyy.ityuyao.entity.Team;
import com.wyy.ityuyao.entity.User;

import java.util.List;

public interface InformationService {

    /**
     * 通过Apply对象查找列表
     *
     * @param apply
     * @param page
     * @param limit
     * @return
     */
    Page<Apply> getApplyList(Apply apply, int page, int limit);

    /**
     * 新增apply
     */
    boolean insertApply(Apply apply);

    /**
     * 通过主管id和用户姓名查询分页列表
     */
    Page<User> selectPageDept(String departmentId, User user, int page, int limit);

    /**
     * 得到未分配部门的员工列表
     */
    List<User> selectUserList();

    /**
     * 删除部门下的员工:也就是删除关联
     *
     * @param id
     * @return
     */
    boolean delUser(String id);

    /**
     * 查询所有团队
     */
    List<Team> selectListTeam();

    /**
     * 添加用户
     * @param ids
     * @param teamId
     * @return
     */
    boolean addUser(String ids, String teamId);
}
