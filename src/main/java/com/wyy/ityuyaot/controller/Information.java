package com.wyy.ityuyaot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Apply;
import com.wyy.ityuyaot.entity.Tomo;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.service.InformationService;
import com.wyy.ityuyaot.service.UserService;
import com.wyy.ityuyaot.util.ItdragonUtils;
import com.wyy.ityuyaot.util.Result;
import com.wyy.ityuyaot.util.ResultResponse;
import com.wyy.ityuyaot.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职工基本信息管理
 *
 * @author 王钰尧
 * @create 2018/10/28 15:07
 * @since 1.0.0
 */
@RequestMapping("/information")
@Controller
public class Information {
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private InformationService informationService;

    @RequestMapping("/house.do")
    public ModelAndView house(ModelAndView mv) {
        User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        User user = userService.getUserByUserName(SessionUser.getUserName());
        mv.addObject("user", user);
        mv.setViewName("information/information");
        return mv;
    }

    /**
     * 普通员工修改信息界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editHouse.do")
    public ModelAndView editHouse(ModelAndView mv) {
        User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        User user = userService.getUserByUserName(SessionUser.getUserName());
        mv.addObject("user", user);
        mv.setViewName("information/editInformation");
        return mv;
    }

    /**
     * 部门主管修改信息界面
     *
     * @param mv
     * @param userId
     * @return
     */
    @RequestMapping("/editDeptUserHouse.do")
    public ModelAndView editDeptUserHouse(ModelAndView mv, String userId) {
        User user = userService.selectByPrimaryKey(userId);
        mv.addObject("user", user);
        mv.setViewName("information/editDeptInformation");
        return mv;
    }

    /**
     * 人事修改信息界面
     *
     * @param mv
     * @param userId
     * @return
     */
    @RequestMapping("/editAllDeptInformation.do")
    public ModelAndView editAllDeptInformation(ModelAndView mv, String userId) {
        User user = userService.selectByPrimaryKey(userId);
        mv.addObject("user", user);
        mv.setViewName("information/editAllDeptInformation");
        return mv;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PutMapping("/edit.do")
    public ResultResponse editUser(User user) {
        boolean result = userService.updateByPrimaryKey(user);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        return Result.resuleSuccess();
    }

    /**
     * 信息修改提交审核
     */
    @ResponseBody
    @PutMapping("/tomo.do")
    public ResultResponse tomo(Tomo tomo) {
        tomo.setStatus(0);
        boolean result = userService.insertTomo(tomo);
        if (!result) {
            return Result.resuleError("提交失败,未知错误");
        }
        return Result.resuleSuccess();
    }

    /**
     * 申请列表
     *
     * @param apply
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("applyTables.do")
    public TableResultResponse userTables(Apply apply, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Apply> pageInfo = informationService.getApplyList(apply, page, limit);
        int i = (page - 1) * limit + 1;
        for (Apply applyEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", applyEntity.getId());
            userMap.put("type", applyEntity.getType());
            userMap.put("main", applyEntity.getMain());
            userMap.put("createdDate", applyEntity.getCreatedDate() == null ? "" : applyEntity.getCreatedDate().substring(0, 19));
            if (applyEntity.getStatus() == 1) {
                userMap.put("status", "待人事审批");
            } else if (applyEntity.getStatus() == 0) {
                userMap.put("status", "待审批");
            } else if (applyEntity.getStatus() == 2) {
                userMap.put("status", "审批通过");
            } else if (applyEntity.getStatus() == 3) {
                userMap.put("status", "驳回");
            }
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 请假申请页面
     */
    @RequestMapping("/leaveHouse.do")
    public ModelAndView leaveHouse(ModelAndView mv) {
        mv.setViewName("information/leave");
        return mv;
    }

    /**
     * 奖励申请页面
     */
    @RequestMapping("/awardHouse.do")
    public ModelAndView awardHouse(ModelAndView mv) {
        mv.setViewName("information/award");
        return mv;
    }

    /**
     * 请假流程提交
     *
     * @param apply
     * @return
     */
    @ResponseBody
    @PostMapping("/leave.do")
    public ResultResponse editUser(Apply apply) {
        User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        apply.setUserName(SessionUser.getUserName());
        apply.setUserId(SessionUser.getId());
        boolean result = informationService.insertApply(apply);
        if (!result) {
            return Result.resuleError("提交失败,未知错误");
        }
        return Result.resuleSuccess();
    }

    /**
     * 部门员工信息页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/informationDept.do")
    public ModelAndView deptHouse(ModelAndView mv) {
        mv.setViewName("information/informationDept");
        return mv;
    }

    /**
     * 人事看员工信息页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/allDeptHouse.do")
    public ModelAndView allDeptHouse(ModelAndView mv) {
        mv.addObject("sign", "sign");
        mv.setViewName("information/informationDept");
        return mv;
    }

    /**
     * 部门员工信息列表
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("deptTables.do")
    public TableResultResponse deptTables(User user, int page, int limit, String sign) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<User> pageInfo = null;
        if ("sign".equals(sign)) {
            pageInfo = informationService.selectPageDept(null, user, page, limit);
        } else {
            User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
            pageInfo = informationService.selectPageDept(SessionUser.getId(), user, page, limit);
        }

        int i = (page - 1) * limit + 1;
        for (User userEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", userEntity.getId());
            userMap.put("userName", userEntity.getUserName());
            userMap.put("realName", userEntity.getRealName());
            userMap.put("deptName", userEntity.getDeptName());
            userMap.put("createdDate", userEntity.getCreatedDate() == null ? "" : userEntity.getCreatedDate().substring(0, 19));
            userMap.put("updateDate", userEntity.getUpdatedDate() == null ? "" : userEntity.getUpdatedDate().substring(0, 19));
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 删除部门员工
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delUser.do")
    @ResponseBody
    public ResultResponse delUser(String id) {
        boolean result = informationService.delUser(id);
        if (!result) {
            Result.resuleError("提交失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 部门员工管理中添加用户的跳转页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addUserHouse")
    public ModelAndView addUserHouse(ModelAndView mv) {
        mv.setViewName("/information/addUser");
        mv.addObject("teamList", informationService.selectListTeam());
        return mv;

    }

    /**
     * 部门员工管理中添加用户中的用户列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("addUserTable.do")
    public TableResultResponse userTablesForRole() {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<User> userList = informationService.selectUserList();
        for (User userEntity : userList) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("id", userEntity.getId());
            userMap.put("userName", userEntity.getUserName());
            userMap.put("realName", userEntity.getRealName());
            userMap.put("createdDate", userEntity.getCreatedDate() == null ? "" : userEntity.getCreatedDate().substring(0, 19));
            if (userEntity.getStatus() == 1) {
                userMap.put("status", "启用");
            } else if (userEntity.getStatus() == 0) {
                userMap.put("status", "禁用");
            }
            infoList.add(userMap);
        }
        return Result.tableResule(0, infoList);
    }

    /**
     * 添加员工
     *
     * @param ids
     * @param teamId
     * @return
     */
    @PostMapping("/addUser.do")
    @ResponseBody
    public ResultResponse delUser(String ids, String teamId) {
        boolean result = informationService.addUser(ids, teamId);
        if (!result) {
            Result.resuleError("提交失败");
        }
        return Result.resuleSuccess();
    }
}