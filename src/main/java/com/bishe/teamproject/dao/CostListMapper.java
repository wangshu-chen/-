package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.CostList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("costListDao")
public interface CostListMapper extends BaseMapper<CostList> {

    /**
     * 根据礼物名称高级查询
     */

    List<CostList> queryCostListAll(@Param("name") String name, @Param("teamId") Integer teamId);

}
