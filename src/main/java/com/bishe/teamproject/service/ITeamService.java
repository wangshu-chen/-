package com.bishe.teamproject.service;

import com.bishe.teamproject.model.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 社团信息管理 服务类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
public interface ITeamService extends IService<Team> {

    /**
     * 查询社团信息管理分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Team>
     */
    IPage<Team> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加社团信息管理
     *
     * @param team 社团信息管理
     * @return int
     */
    int add(Team team);

    /**
     * 删除社团信息管理
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改社团信息管理
     *
     * @param team 社团信息管理
     * @return int
     */
    int updateData(Team team);

    /**
     * id查询数据
     *
     * @param id id
     * @return Team
     */
    Team findById(Long id);

    /**
     * 分页高级查询
     */
    PageInfo<Team> queryTeamList(int page , int limi , Team team);

    /**
     * 根据用户id查询社团信息
     */
    Team queryTeamInfoByUserId(Integer userId);

    /**
     * 查询我的社团列表
     */
    List<Team> queryMyteamList(String tel);
}
