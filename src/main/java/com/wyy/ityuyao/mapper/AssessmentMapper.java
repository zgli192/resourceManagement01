package com.wyy.ityuyao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyy.ityuyao.entity.Assessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
/**
 * 角色数据访问层  ??? 这个应该是部门的表关联
 */
public interface AssessmentMapper extends BaseMapper<Assessment> {
    List<Assessment> selectAssessment(RowBounds var1,
                                      @Param("departmentId") String departmentId, @Param("assessment") Assessment assessment);
}
