package com.wyy.ityuyaot.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Apply;
import com.wyy.ityuyaot.entity.Tomo;
import com.wyy.ityuyaot.entity.User;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/29 16:21
 * @since 1.0.0
 */
public interface ApplyService {

    Page<Apply> selectApply(int page, int limit, String departmentId, Integer status);

    Page<Tomo> selectTomo(int page, int limit);

    boolean updateStatus(String id, Integer status);

    boolean updateTomoStatus(String id, Integer status);

    boolean updateUser(User user);
}