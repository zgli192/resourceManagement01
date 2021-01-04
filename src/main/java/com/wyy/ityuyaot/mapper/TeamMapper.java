package com.wyy.ityuyaot.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyy.ityuyaot.entity.Team;
import com.wyy.ityuyaot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
/**
 * 角色数据访问层
 */
public interface TeamMapper extends BaseMapper<Team> {
    /**
     * 根据teamId查询员工
     *
     * @param departmentId
     * @return
     */
    List<User> selectUser(RowBounds var1, @Param(value = "departmentId") String departmentId,
                          @Param(value = "userName") String userName,
                          @Param(value = "administrative") String administrative,
                          @Param(value = "position") String position,
                          @Param(value = "title") String title,
                          @Param(value = "employeeType") String employeeType);

    int delUser(@Param(value = "userId") String userId);

    List<String> selectUserIdfrom();

    int addUserIdAndTeamId(@Param(value = "userId") String userId, @Param(value = "teamId") String teamId);

}
