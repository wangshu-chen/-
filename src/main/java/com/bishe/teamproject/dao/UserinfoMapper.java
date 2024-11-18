package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 管理员信息表 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("userInfoDao")
public interface UserinfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据对象属性值查询对象是否存在
     * 用户名 密码 角色
     */

    UserInfo queryUserInfoByUserNameAndPasswordAndType(UserInfo userInfo);

    /**
     * 高价查询
     */
    List<UserInfo> queryUserInfoList(UserInfo userInfo);

    /**
     * 修改用户密码
     */
    int updatePassordByIdAndNewPassword(@Param("id") Integer id,@Param("pwd") String pwd);
}
