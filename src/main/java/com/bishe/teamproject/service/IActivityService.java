package com.bishe.teamproject.service;

import com.bishe.teamproject.model.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 社团活动管理 服务类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
public interface IActivityService extends IService<Activity> {

    /**
     * 查询社团活动管理分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Activity>
     */
    IPage<Activity> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加社团活动管理
     *
     * @param activity 社团活动管理
     * @return int
     */
    int add(Activity activity);

    /**
     * 删除社团活动管理
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改社团活动管理
     *
     * @param activity 社团活动管理
     * @return int
     */
    int updateData(Activity activity);

    /**
     * id查询数据
     *
     * @param id id
     * @return Activity
     */
    Activity findById(Long id);

    /**
     * 高级查询
     */
    PageInfo<Activity> queryList(int page,int limit,String name,String tel,Integer userId);


    /**
     * 根据ID修改状态
     */
    boolean updateStatus(int status,int id);

    /**
     * 根据ID获取最新的活动信息
     */

    Activity findByTeamId(Integer teamId);

}
