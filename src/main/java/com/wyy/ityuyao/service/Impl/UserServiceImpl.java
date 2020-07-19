package com.wyy.ityuyao.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Permission;
import com.wyy.ityuyao.entity.Role;
import com.wyy.ityuyao.entity.Tomo;
import com.wyy.ityuyao.entity.User;
import com.wyy.ityuyao.mapper.PermissionMapper;
import com.wyy.ityuyao.mapper.RoleMapper;
import com.wyy.ityuyao.mapper.TomoMapper;
import com.wyy.ityuyao.mapper.UserMapper;
import com.wyy.ityuyao.service.UserService;
import com.wyy.ityuyao.util.DateUtil;
import com.wyy.ityuyao.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户service实现类
 *
 * @author 王钰尧
 * @create 2018/10/7 15:40
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private TomoMapper tomoMapper;

    @Override
    public User findUserByUserName(String userName) {
        User searchUser = new User();
        searchUser.setUserName(userName);
        User user = userMapper.selectOne(searchUser);
        if (user != null) {
            List<Role> roleList = roleMapper.selectRoleListByUserId(user.getId());
            if (!roleList.isEmpty()) {
                for (Role role : roleList) {
                    List<Permission> permissionList = permissionMapper.selectPermissionByRoleId(role.getId());
                    role.setPermissions(permissionList);
                }
            }
            user.setRoleList(roleList);
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        User searchUser = new User();
        searchUser.setUserName(userName);
        User user = userMapper.selectOne(searchUser);
        return user;
    }

    @Override
    public Page<User> getUserList(User user, int page, int limit) {
        EntityWrapper<User> searchInfo = new EntityWrapper<>();
        if (user != null) {
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                searchInfo.eq("userName", user.getUserName());
            }
            if (user.getStatus() != null && !"".equals(user.getStatus())) {
                searchInfo.eq("status", user.getStatus());
            }
        }
        searchInfo.ne("userName", "adminStrator");
        searchInfo.orderBy("createdDate desc");
        Page<User> pageInfo = new Page<>(page, limit);
        List<User> userList = userMapper.selectPage(pageInfo, searchInfo);
        if (!userList.isEmpty()) {
            pageInfo.setRecords(userList);
        }
        return pageInfo;
    }

    @Override
    public Page<User> getUserListForRole(User user, int page, int limit, String roleId) {
        EntityWrapper<User> searchInfo = new EntityWrapper<>();
        if (user != null) {
            if (user.getStatus() != null && !"".equals(user.getStatus())) {
                searchInfo.eq("status", user.getStatus());
            }
        }
        searchInfo.ne("userName", "adminStrator");
        searchInfo.orderBy("createdDate desc");
        Page<User> pageInfo = new Page<>(page, limit);
        List<User> userList = userMapper.selectPage(pageInfo, searchInfo);
        List<String> userIdList = roleMapper.slectRoleAndUser(roleId);
        List<User> resultList = new ArrayList<>();
        for (User user1 : userList) {
            if (!userIdList.contains(user1.getId())) {
                resultList.add(user1);
            }
        }
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Page<User> getUserListByRoleId(String roleId, int page, int limit) {
        Page<User> pageInfo = new Page<>(page, limit);
        List<User> userList = userMapper.selectUserListByRoleId(pageInfo, roleId);
        if (!userList.isEmpty()) {
            pageInfo.setRecords(userList);
        }
        return pageInfo;
    }

    @Override
    public boolean changePass(String newPas, String userName) {
        User user = new User();
        user.setPlainPassword(newPas);
        EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
        entityWrapper.eq("userName", userName);
        itdragonUtils.entryptPassword(user);
        int result = userMapper.update(user, entityWrapper);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserStatus(String id, Integer status) {
        User user = new User();
        user.setStatus(status);
        user.setId(id);
        int result = userMapper.updateById(user);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean insert(User user) {
        user.setStatus(1);
        user.setCreatedDate(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        itdragonUtils.entryptPassword(user);
        int result = userMapper.insert(user);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateLoginTime(String userName, String time) {
        User user = new User();
        EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
        user.setUpdatedDate(time);
        entityWrapper.eq("userName", userName);
        userMapper.update(user, entityWrapper);
    }

    @Override
    public List<User> selectList() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
        entityWrapper.ne("userName", "adminStrator");
        entityWrapper.ne("userName", "admin");
        return userMapper.selectList(entityWrapper);
    }

    @Override
    public boolean deleteByPrimaryKey(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = userMapper.deleteById(s);
            if (result > 0) {
                roleMapper.delUserByUserId(s);
            }
        }
        if (result > 0) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean updateByPrimaryKey(User user) {
        User userVerify = userMapper.selectById(user.getId());
        if (!user.getPlainPassword().equals(userVerify.getPassword())) {
            itdragonUtils.entryptPassword(user);
        }
        int result = userMapper.updateById(user);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean insertTomo(Tomo tomo) {
        int result = tomoMapper.insert(tomo);
        if (result > 0) {
            return true;
        }
        return false;
    }


}