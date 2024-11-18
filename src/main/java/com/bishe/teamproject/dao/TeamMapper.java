package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 社团信息管理 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("teamDao")
public interface TeamMapper extends BaseMapper<Team> {

    /**
     * 高级查询
     */
    List<Team> queryTeamList(Team team);

    /**
     * 根据用户id查询社团信息
     */
    Team queryTeamInfoByUserId(@Param("userId") Integer userId);

    /**
     * 根据社团id查询社团信息
     */
    Team queryById(Long id);

    /**
     * 查询我的社团列表
     */
    List<Team> queryMyteamList(@Param("tel") String tel);


}
