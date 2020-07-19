package com.wyy.ityuyao.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Assessment;
import com.wyy.ityuyao.mapper.AssessmentMapper;
import com.wyy.ityuyao.service.AssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/30 14:50
 * @since 1.0.0
 */
@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Resource
    private AssessmentMapper assessmentMapper;

    @Override
    public Assessment selectOne(String id) {
        return assessmentMapper.selectById(id);
    }

    @Override
    public Page<Assessment> selectPage(Assessment assessment,String departmentId, int page, int limit) {
        Page<Assessment> pageInfo = new Page<>(page, limit);
        List<Assessment> userList = assessmentMapper.selectAssessment(pageInfo,departmentId,assessment);
        if (!userList.isEmpty()) {
            pageInfo.setRecords(userList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Assessment assessment) {
        int result = assessmentMapper.insert(assessment);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Assessment assessment) {
        int result = assessmentMapper.updateById(assessment);
        if (result > 0) {
            return true;
        }
        return false;
    }
}