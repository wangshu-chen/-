package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.ApplyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.teamproject.model.ApplyList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 社团入团申请记录,申请生成可入团 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("applyDao")
public interface ApplyInfoMapper extends BaseMapper<ApplyInfo> {

    /**
     * 查询以及高级查询
     */
    List<ApplyInfo> queryAppList(ApplyInfo info);

    /**
     * 根据申请ID 查询审核记录
     */
    List<ApplyList> queryAppListByAppId(@Param("appId") Integer appId);

    /**
     * 修改状态
     */
    boolean updateStatus(ApplyInfo info);

    /**
     * 添加审批记录信息
     */
    int insertAppList(ApplyList applyList);

    /**
     * 根据电话和社团ID判断是否存在该对象
     */
    ApplyInfo queryApplyInfoByTeamIdAndTel(@Param("teamId") int teamId,
                                           @Param("tel")String tel);

}
