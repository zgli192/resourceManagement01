package com.wyy.ityuyao.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Team;
import com.wyy.ityuyao.mapper.TeamMapper;
import com.wyy.ityuyao.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/30 9:54
 * @since 1.0.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private TeamMapper teamMapper;

    @Override
    public Page<Team> selectPage(Team team, int page, int limit) {
        EntityWrapper<Team> searchInfo = new EntityWrapper<>();
        if (team != null) {
            if (team.getName() != null && !"".equals(team.getName())) {
                searchInfo.eq("name", team.getName());
            }
            if (team.getDepartmentId() != null && !"".equals(team.getDepartmentId())) {
                searchInfo.eq("departmentId", team.getDepartmentId());
            }
            if (team.getDepartmentName() != null && !"".equals(team.getDepartmentName())) {
                searchInfo.eq("departmentName", team.getDepartmentName());
            }
        }
        Page<Team> pageInfo = new Page<>(page, limit);
        List<Team> userList = teamMapper.selectPage(pageInfo, searchInfo);
        if (!userList.isEmpty()) {
            pageInfo.setRecords(userList);
        }
        return pageInfo;
    }

    @Override
    public boolean add(Team team) {
        int result = teamMapper.insert(team);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Team team) {
        int result = teamMapper.updateById(team);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean del(String id) {
        int result = teamMapper.deleteById(id);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Team selectOne(String id) {
        return teamMapper.selectById(id);
    }

    @Override
    public Team selectOneByDepartmentId(String departmentId) {
        Team team = new Team();
        team.setDepartmentId(departmentId);
        return teamMapper.selectOne(team);
    }
}