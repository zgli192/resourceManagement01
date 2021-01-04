package com.wyy.ityuyaot.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Apply;
import com.wyy.ityuyaot.entity.Tomo;
import com.wyy.ityuyaot.entity.User;
import com.wyy.ityuyaot.mapper.ApplyMapper;
import com.wyy.ityuyaot.mapper.TomoMapper;
import com.wyy.ityuyaot.mapper.UserMapper;
import com.wyy.ityuyaot.service.ApplyService;
import com.wyy.ityuyaot.util.ItdragonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/10/29 16:26
 * @since 1.0.0
 */
@Service
public class ApplyInfoServiceImpl implements ApplyService {
    @Resource
    private ApplyMapper applyMapper;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TomoMapper tomoMapper;

    @Override
    public Page<Apply> selectApply(int page, int limit, String departmentId, Integer status) {
        Page<Apply> pageInfo = new Page<>(page, limit);
        List<Apply> applyList = applyMapper.selectApply(pageInfo, departmentId, status);
        if (!applyList.isEmpty()) {
            pageInfo.setRecords(applyList);
        }
        return pageInfo;
    }

    @Override
    public Page<Tomo> selectTomo(int page, int limit) {
        Page<Tomo> pageInfo = new Page<>(page, limit);
        EntityWrapper<Tomo> searchInfo = new EntityWrapper<>();
        searchInfo.eq("status", 0);
        List<Tomo> tomos = tomoMapper.selectPage(pageInfo, searchInfo);
        pageInfo.setRecords(tomos);
        return pageInfo;
    }

    @Override
    public boolean updateStatus(String id, Integer status) {
        Apply apply = new Apply();
        apply.setId(id);
        apply.setStatus(status);
        int result = applyMapper.updateById(apply);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTomoStatus(String id, Integer status) {
        Tomo tomo = new Tomo();
        tomo.setId(id);
        tomo.setStatus(status);
        int result = tomoMapper.updateById(tomo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.updateById(user);
        if (result > 0) {
            return true;
        }
        return false;
    }
}