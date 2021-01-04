package com.wyy.ityuyaot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Assessment;
import com.wyy.ityuyaot.entity.HkList;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.service.AssessmentService;
import com.wyy.ityuyaot.service.DeptService;
import com.wyy.ityuyaot.service.Impl.Json2HkListImpl;
import com.wyy.ityuyaot.service.UserService;
import com.wyy.ityuyaot.util.ItdragonUtils;
import com.wyy.ityuyaot.util.Result;
import com.wyy.ityuyaot.util.ResultResponse;
import com.wyy.ityuyaot.util.TableResultResponse;
import exceptions.TimerSettingError;
import ga.ArtemisPostTest;
import ga.ArtemisPostTestImpl;
import ga.Json2HkList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    private static final Logger LOGGER = Logger.getLogger(AssessmentController.class);

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
            //权限控制，此次是admin的权限
            pageInfo = assessmentService.selectPage(assessment, null, page, limit);
        }
        int i = (page - 1) * limit + 1;
        //获取海康数据
        String result = null;
        ArtemisPostTest artemisPostTest = new ArtemisPostTestImpl();
        try {
//                获取海康的原始数据
            result = artemisPostTest.callPostStringApiOne(assessment.getTime(), assessment.getUserName());
            LOGGER.info("海康原始数据" + result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimerSettingError timerSettingError) {
            timerSettingError.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 转换到HkLists

        Page<HkList> pageInfoOne = null;
        try {
            pageInfoOne = Json2HkListImpl.getHkList(result);

            for (HkList applyEntity : pageInfoOne.getRecords()) {
                Map<String, Object> userMap = new HashMap<>(16);
                userMap.put("index", i + "");
                //  userMap.put("id", applyEntity.getId());
                userMap.put("userName", applyEntity.getPersonName());
                userMap.put("dept", applyEntity.getOrgName());
                userMap.put("time", applyEntity.getEventId());
                userMap.put("attendance", applyEntity.getEventName());
                userMap.put("performance", applyEntity.getDevName());
                userMap.put("absenteeism", applyEntity.getCardNo());
           /* userMap.put("late", applyEntity.getLate());
            userMap.put("vacate", applyEntity.getVacate());*/
                infoList.add(userMap);
                i++;
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info(pageInfoOne.getTotal());
        return Result.tableResule(pageInfoOne.getTotal(), infoList);
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