package com.wyy.ityuyaot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.service.InformationService;
import com.wyy.ityuyaot.service.UserService;
import com.wyy.ityuyaot.util.ItdragonUtils;
import com.wyy.ityuyaot.util.Result;
import com.wyy.ityuyaot.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @create 2018/10/30 8:52
 * @since 1.0.0
 */
@Controller
@RequestMapping("/welfare")
public class Welfare {
    @Autowired
    private InformationService informationService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;

    @RequestMapping("/welfare.do")
    public ModelAndView welfare(ModelAndView modelAndView) {
        modelAndView.setViewName("/welfare/welfare");
        return modelAndView;
    }

    @RequestMapping("/welfareAll.do")
    public ModelAndView welfareAll(ModelAndView modelAndView) {
        modelAndView.addObject("sign", "sign");
        modelAndView.setViewName("/welfare/welfare");
        return modelAndView;
    }
    @RequestMapping("/editHouser.do")
    public ModelAndView editHouser(ModelAndView modelAndView,String userId) {
        User user =userService.selectByPrimaryKey(userId);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/welfare/editWelfare");
        return modelAndView;
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
    @GetMapping("/welfareTables.do")
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
            userMap.put("annualLeave", userEntity.getAnnualLeave());
            userMap.put("wage", userEntity.getWage());
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }
}