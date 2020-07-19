package com.wyy.ityuyao.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyao.entity.Assessment;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/30 14:47
 * @since 1.0.0
 */
public interface AssessmentService {

    Assessment selectOne(String id);

    Page<Assessment> selectPage(Assessment assessment, String departmentId, int page, int limit);

    boolean insert(Assessment assessment);

    boolean update(Assessment assessment);
}