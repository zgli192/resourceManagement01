package com.wyy.ityuyaot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Team;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.service.DeptService;
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
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/30 9:40
 * @since 1.0.0
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;

    @RequestMapping("/dept.do")
    public ModelAndView dept(ModelAndView mv) {
        User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        Team team = deptService.selectOneByDepartmentId(SessionUser.getId());
        mv.addObject("team", team);
        mv.setViewName("/dept/dept");
        return mv;
    }

    @RequestMapping("/deptAll.do")
    public ModelAndView deptAll(ModelAndView mv) {
        mv.setViewName("/dept/deptAll");
        return mv;
    }

    @RequestMapping("/addHouse.do")
    public ModelAndView addHouse(ModelAndView mv) {
        List<User> userList = userService.selectList();
        mv.addObject("userList", userList);
        mv.setViewName("/dept/addDept");
        return mv;
    }

    @RequestMapping("/editHouse.do")
    public ModelAndView editHouse(ModelAndView mv, String id) {
        Team team = deptService.selectOne(id);
        List<User> userList = userService.selectList();
        mv.addObject("userList", userList);
        mv.addObject("team", team);
        mv.setViewName("/dept/editDept");
        return mv;
    }

    /**
     * 列表显示
     *
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("/deptTables.do")
    public TableResultResponse deptTables(Team team, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Team> pageInfo = deptService.selectPage(team, page, limit);
        int i = (page - 1) * limit + 1;
        for (Team userEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", userEntity.getId());
            userMap.put("name", userEntity.getName());
            userMap.put("departmentName", userEntity.getDepartmentName());
            userMap.put("departmentId", userEntity.getDepartmentId());
            userMap.put("responsibility", userEntity.getResponsibility());
            userMap.put("phone", userEntity.getPhone());
            userMap.put("compensation", userEntity.getCompensation());
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增
     *
     * @param team
     * @return
     */
    @ResponseBody
    @PostMapping("/add.do")
    public ResultResponse addUser(Team team) {
        team.setDepartmentId(team.getConten().split(",")[1]);
        team.setDepartmentName(team.getConten().split(",")[0]);
        boolean result = deptService.add(team);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    @ResponseBody
    @DeleteMapping("/del.do")
    public ResultResponse delUser(String id) {
        boolean result = deptService.del(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    @ResponseBody
    @PutMapping("/edit.do")
    public ResultResponse editUser(Team team) {
        team.setDepartmentId(team.getConten().split(",")[1]);
        team.setDepartmentName(team.getConten().split(",")[0]);
        boolean result = deptService.edit(team);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        return Result.resuleSuccess();
    }
}