package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.TeamUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 社团成员管理 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */

@Component("teamUserDao")
public interface TeamUserMapper extends BaseMapper<TeamUser> {

    /**
     * 高级查询
     */

    List<TeamUser> queryTeamUserList(@Param("email") String email,@Param("tel") String tel,@Param("userId")Integer userId);

    /**
     * 根据电话和社团ID判断是否存在该对象
     */
    TeamUser queryTeamUserByTeamIdAndTel(@Param("teamId") int teamId, @Param("tel") String tel);

    /**
     * 根据用户名 密码查询是否有当前对象
     */
    TeamUser queryByUserNameAndPwd(@Param("username")String username,@Param("password")String password);

}
