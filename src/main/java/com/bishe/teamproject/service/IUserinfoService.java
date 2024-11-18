package com.bishe.teamproject.service;

import com.bishe.teamproject.model.LeaveInfo;
import com.bishe.teamproject.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 管理员信息表 服务类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
public interface IUserinfoService extends IService<UserInfo> {

    /**
     * 查询管理员信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Userinfo>
     */
    IPage<UserInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加管理员信息表
     *
     * @param userinfo 管理员信息表
     * @return int
     */
    int add(UserInfo userinfo);

    /**
     * 删除管理员信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改管理员信息表
     *
     * @param userinfo 管理员信息表
     * @return int
     */
    int updateData(UserInfo userinfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return Userinfo
     */
    UserInfo findById(Long id);

    UserInfo queryUserInfoByUserNameAndPasswordAndType(UserInfo userinfo);

    /**
     * 高级查询
     */
    PageInfo<UserInfo> queryUserInfoAll(int page, int limit, UserInfo userInfo);

    /**
     * 修改用户密码
     */
    int updatepassword(Integer id,String newpassword);
}
