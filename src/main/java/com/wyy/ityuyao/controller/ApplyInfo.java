package com.wyy.ityuyao.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Apply;
import com.wyy.ityuyao.entity.Tomo;
import com.wyy.ityuyao.entity.User;
import com.wyy.ityuyao.service.ApplyService;
import com.wyy.ityuyao.service.UserService;
import com.wyy.ityuyao.util.ItdragonUtils;
import com.wyy.ityuyao.util.Result;
import com.wyy.ityuyao.util.ResultResponse;
import com.wyy.ityuyao.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/29 16:20
 * @since 1.0.0
 */
@Controller
@RequestMapping("/applyInfo")
public class ApplyInfo {
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;

    /**
     * 主页跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/applyInfo")
    public ModelAndView applyInfoHouse(ModelAndView mv) {
        mv.setViewName("/applyInfo/applyInfo");
        mv.addObject("status", 0);
        return mv;
    }

    /**
     * 主页跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/applyInfoAll")
    public ModelAndView applyInfoAll(ModelAndView mv) {
        mv.addObject("sign", "all");
        mv.addObject("status", 1);
        mv.setViewName("/applyInfo/applyInfo");
        return mv;
    }

    @GetMapping("/applyInfoTable")
    @ResponseBody
    public TableResultResponse applyInfoTable(int page, int limit, Integer status, String sign) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Apply> pageInfo = null;
        if ("all".equals(sign)) {
            pageInfo = applyService.selectApply(page, limit, null, status);
        } else {
            User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
            pageInfo = applyService.selectApply(page, limit, SessionUser.getId(), status);
        }
        int i = (page - 1) * limit + 1;
        for (Apply applyEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", applyEntity.getId());
            userMap.put("userName", applyEntity.getUserName());
            userMap.put("type", applyEntity.getType());
            userMap.put("main", applyEntity.getMain());
            userMap.put("createdDate", applyEntity.getCreatedDate() == null ? "" : applyEntity.getCreatedDate().substring(0, 19));
            if (applyEntity.getStatus() == 0 || applyEntity.getStatus() == 1) {
                userMap.put("status", "待审核");
            }
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 待修改审核
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/tomoTable")
    @ResponseBody
    public TableResultResponse tomoTable(int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Tomo> pageInfo = applyService.selectTomo(page, limit);
        int i = (page - 1) * limit + 1;
        for (Tomo applyEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", applyEntity.getId());
            userMap.put("userId", applyEntity.getUserId());
            userMap.put("userName", userService.selectByPrimaryKey(applyEntity.getUserId()).getUserName());
            userMap.put("annualLeave", applyEntity.getAnnualLeave());
            userMap.put("craft", applyEntity.getCraft());
            userMap.put("administrative", applyEntity.getAdministrative());
            userMap.put("title", applyEntity.getTitle());
            userMap.put("position", applyEntity.getPosition());
            userMap.put("employeeType", applyEntity.getEmployeeType());
            userMap.put("seniority", applyEntity.getSeniority());
            userMap.put("wage", applyEntity.getWage());
            if (applyEntity.getStatus() == 0) {
                userMap.put("status", "待审核");
            }
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 更改状态
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @PostMapping("status.do")
    public ResultResponse updateUserStatus(String id, Integer status) {
        boolean result = applyService.updateStatus(id, status);
        if (!result) {
            return Result.resuleError("更改失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 更改状态
     *
     * @return
     */
    @ResponseBody
    @PostMapping("editUser.do")
    public ResultResponse updateUserStatus(Tomo tomo) {
        User user = new User();
        user.setId(tomo.getUserId());
        user.setAnnualLeave(tomo.getAnnualLeave());
        user.setCraft(tomo.getCraft());
        user.setAdministrative(tomo.getAdministrative());
        user.setTitle(tomo.getTitle());
        user.setPosition(tomo.getPosition());
        user.setEmployeeType(tomo.getEmployeeType());
        user.setSeniority(tomo.getSeniority());
        user.setWage(tomo.getWage());
        boolean result = applyService.updateUser(user);
        if (result) {
            applyService.updateTomoStatus(tomo.getId(), 1);
        }
        if (!result) {
            return Result.resuleError("更改失败");
        }
        return Result.resuleSuccess();
    }
}