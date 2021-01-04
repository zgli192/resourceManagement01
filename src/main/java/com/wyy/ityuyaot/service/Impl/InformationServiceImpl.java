package com.wyy.ityuyaot.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Apply;
import com.wyy.ityuyaot.entity.Team;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.mapper.ApplyMapper;
import com.wyy.ityuyaot.mapper.TeamMapper;
import com.wyy.ityuyaot.mapper.UserMapper;
import com.wyy.ityuyaot.service.InformationService;
import com.wyy.ityuyaot.util.DateUtil;
import com.wyy.ityuyaot.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/28 17:24
 * @since 1.0.0
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private ApplyMapper applyMapper;
    @Resource
    private TeamMapper teamMapper;
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private UserMapper userMapper;

    @Override
    public Page<Apply> getApplyList(Apply apply, int page, int limit) {
        EntityWrapper<Apply> searchInfo = new EntityWrapper<>();
        if (apply.getUserName() != null && !"".equals(apply.getUserName())) {
            searchInfo.eq("userName", apply.getUserName());
        }
        Page<Apply> pageInfo = new Page<>(page, limit);
        List<Apply> applyList = applyMapper.selectPage(pageInfo, searchInfo);
        if (!applyList.isEmpty()) {
            pageInfo.setRecords(applyList);
        }
        return pageInfo;
    }

    @Override
    public boolean insertApply(Apply apply) {
        apply.setStatus(0);
        apply.setCreatedDate(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        int result = applyMapper.insert(apply);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Page<User> selectPageDept(String departmentId, User user, int page, int limit) {
        Page<User> pageInfo = new Page<>(page, limit);
        List<User> userList = teamMapper.selectUser(pageInfo, departmentId, user.getUserName(),user.getAdministrative(),user.getPosition(),user.getTitle(),user.getEmployeeType());
        if (!userList.isEmpty()) {
            pageInfo.setRecords(userList);
        }
        return pageInfo;
    }

    @Override
    public List<User> selectUserList() {
        EntityWrapper<User> searchInfo = new EntityWrapper<>();
        searchInfo.ne("username", "admin");
        searchInfo.ne("username", "adminStrator");
        List<User> userList = userMapper.selectList(searchInfo);
        List<String> userIds = teamMapper.selectUserIdfrom();
        List<User> resultList = new ArrayList<>();
        for (User user1 : userList) {
            if (!userIds.contains(user1.getId())) {
                resultList.add(user1);
            }
        }
        return resultList;
    }

    @Override
    public boolean delUser(String id) {
        int result = teamMapper.delUser(id);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Team> selectListTeam() {
        return teamMapper.selectList(null);
    }

    @Override
    public boolean addUser(String ids, String teamId) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = teamMapper.addUserIdAndTeamId(s, teamId);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}