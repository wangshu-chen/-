package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 社团活动管理 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("activityDao")
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 支持高级查询的接口
     * name:活动名称
     * tel:活动电话
     * userId:所属社团团长用户ID
     */

    List<Activity> queryActivityInfoList(@Param("name") String name,
                                         @Param("tel") String tel,
                                         @Param("userId") Integer userId);

    /**
     * 审核记录信息
     */

    int updateStatusById(@Param("status") Integer status,@Param("id")Integer id);

    /**
     * 根据ID获取最新的活动信息
     */

    Activity findByTeamId(@Param("teamId") Integer teamId);

}
