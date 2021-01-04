package com.wyy.ityuyaot.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Team;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/29 16:21
 * @since 1.0.0
 */
public interface DeptService {
    /**
     * 分页查询
     *
     * @param team
     * @return
     */
    Page<Team> selectPage(Team team, int page, int limit);

    /**
     * 新增
     */
    boolean add(Team team);

    /**
     * 修改
     */
    boolean edit(Team team);

    /**
     * 删除
     */
    boolean del(String id);

    /**
     * 查找单个
     */
    Team selectOne(String id);

    Team selectOneByDepartmentId(String departmentId);
}