package com.bishe.teamproject.service.Impl;

import com.bishe.teamproject.model.LeaveInfo;
import com.bishe.teamproject.model.UserInfo;
import com.bishe.teamproject.dao.UserinfoMapper;
import com.bishe.teamproject.service.IUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, UserInfo> implements IUserinfoService {

    @Autowired
    private UserinfoMapper userInfoDao;

    @Override
    public  IPage<UserInfo> findListByPage(Integer page, Integer pageCount){
        IPage<UserInfo> wherePage = new Page<>(page, pageCount);
        UserInfo where = new UserInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(UserInfo userinfo){
        return baseMapper.insert(userinfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(UserInfo userinfo){
        return baseMapper.updateById(userinfo);
    }

    @Override
    public UserInfo findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public UserInfo queryUserInfoByUserNameAndPasswordAndType(UserInfo userinfo) {
        return userInfoDao.queryUserInfoByUserNameAndPasswordAndType(userinfo);
    }

    @Override
    public PageInfo<UserInfo> queryUserInfoAll(int page, int limit, UserInfo userInfo) {
        PageHelper.startPage(page,limit);
        List<UserInfo> leaveInfos = userInfoDao.queryUserInfoList(userInfo);
        PageInfo<UserInfo> pageInfo = new PageInfo(leaveInfos);
        return pageInfo;
    }

    @Override
    public int updatepassword(Integer id, String newpassword) {
        return userInfoDao.updatePassordByIdAndNewPassword(id,newpassword);
    }
}
