package com.wyy.ityuyao.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Assessment;
import com.wyy.ityuyao.entity.User;
import com.wyy.ityuyao.service.AssessmentService;
import com.wyy.ityuyao.service.DeptService;
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
 * @create 2018/10/30 14:55
 * @since 1.0.0
 */
@Controller
@RequestMapping("/assessment")
public class AssessmentController {
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private AssessmentService assessmentService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;

    @RequestMapping("/assessment.do")
    public ModelAndView assessment(ModelAndView mv) {
        mv.setViewName("/assessment/assessment");
        return mv;
    }

    @RequestMapping("/assessmentAll.do")
    public ModelAndView dept(ModelAndView mv) {
        mv.addObject("sign", "all");
        mv.setViewName("/assessment/assessment");
        return mv;
    }

    @GetMapping("/assessmentTable")
    @ResponseBody
    public TableResultResponse applyInfoTable(int page, int limit, Assessment assessment, String sign) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Assessment> pageInfo = null;
        if ("all".equals(sign)) {
            pageInfo = assessmentService.selectPage(assessment, null, page, limit);
        } else {
            User SessionUser = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
            pageInfo = assessmentService.selectPage(assessment, SessionUser.getId(), page, limit);
        }
        int i = (page - 1) * limit + 1;
        for (Assessment applyEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", applyEntity.getId());
            userMap.put("userName", applyEntity.getUserName());
            userMap.put("dept", applyEntity.getDept());
            userMap.put("attendance", applyEntity.getAttendance());
            userMap.put("performance", applyEntity.getPerformance());
            userMap.put("absenteeism", applyEntity.getAbsenteeism());
            userMap.put("late", applyEntity.getLate());
            userMap.put("vacate", applyEntity.getVacate());
            userMap.put("time", applyEntity.getTime());
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    @RequestMapping("/addHouse.do")
    public ModelAndView addHouse(ModelAndView mv) {
        List<User> userList = userService.selectList();
        mv.addObject("userList", userList);
        mv.setViewName("/assessment/addAssessment");
        return mv;
    }

    /**
     * 新增
     *
     * @param assessment
     * @return
     */
    @ResponseBody
    @PostMapping("/add.do")
    public ResultResponse addUser(Assessment assessment) {
        assessment.setUserName(assessment.getContent().split(",")[0]);
        assessment.setUserId(assessment.getContent().split(",")[1]);
        boolean result = assessmentService.insert(assessment);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    @RequestMapping("/editHouse.do")
    public ModelAndView editHouse(ModelAndView mv, String id) {
        Assessment assessment = assessmentService.selectOne(id);
        List<User> userList = userService.selectList();
        mv.addObject("userList", userList);
        mv.addObject("assessment", assessment);
        mv.setViewName("/assessment/editAssessment");
        return mv;
    }

    @ResponseBody
    @PostMapping("/edit.do")
    public ResultResponse edit(Assessment assessment) {
        assessment.setUserName(assessment.getContent().split(",")[0]);
        assessment.setUserId(assessment.getContent().split(",")[1]);
        boolean result = assessmentService.update(assessment);
        if (!result) {
            return Result.resuleError("修改成功");
        }
        return Result.resuleSuccess();
    }
}