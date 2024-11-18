package com.bishe.teamproject.service;

import com.bishe.teamproject.model.ApplyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bishe.teamproject.model.ApplyList;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 社团入团申请记录,申请生成可入团 服务类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
public interface IApplyInfoService extends IService<ApplyInfo> {

    /**
     * 查询社团入团申请记录,申请生成可入团分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<ApplyInfo>
     */
    IPage<ApplyInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加社团入团申请记录,申请生成可入团
     *
     * @param applyInfo 社团入团申请记录,申请生成可入团
     * @return int
     */
    int add(ApplyInfo applyInfo);

    /**
     * 删除社团入团申请记录,申请生成可入团
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改社团入团申请记录,申请生成可入团
     *
     * @param applyInfo 社团入团申请记录,申请生成可入团
     * @return int
     */
    int updateData(ApplyInfo applyInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return ApplyInfo
     */
    ApplyInfo findById(Long id);

    /**
     * 分页和高级查询
     */
    PageInfo<ApplyInfo> queryAppInfoByPage(int page,int limit,ApplyInfo applyInfo);


    /**
     * 根据申请ID 查询审核记录
     */
    List<ApplyList> queryAppListByAppId(Integer appId);

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
    ApplyInfo queryApplyInfoByTeamIdAndTel(int teamId, String tel);
}
