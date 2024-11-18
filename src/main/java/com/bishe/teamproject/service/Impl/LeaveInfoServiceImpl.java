package com.bishe.teamproject.service.Impl;

import com.bishe.teamproject.model.Activity;
import com.bishe.teamproject.model.LeaveInfo;
import com.bishe.teamproject.dao.LeaveInfoMapper;
import com.bishe.teamproject.service.ILeaveInfoService;
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
 * 在线留言管理 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Service
public class LeaveInfoServiceImpl extends ServiceImpl<LeaveInfoMapper, LeaveInfo> implements ILeaveInfoService {

    @Autowired
    private LeaveInfoMapper leaveDao;

    @Override
    public  IPage<LeaveInfo> findListByPage(Integer page, Integer pageCount){
        IPage<LeaveInfo> wherePage = new Page<>(page, pageCount);
        LeaveInfo where = new LeaveInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(LeaveInfo leaveInfo){
        return baseMapper.insert(leaveInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(LeaveInfo leaveInfo){
        return baseMapper.updateById(leaveInfo);
    }

    @Override
    public LeaveInfo findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<LeaveInfo> queryLeaveInfoAll(int page, int limit, String tel) {
        PageHelper.startPage(page,limit);
        List<LeaveInfo> leaveInfos = leaveDao.queryLeaveInfoAll(tel);
        PageInfo<LeaveInfo> pageInfo = new PageInfo(leaveInfos);
        return pageInfo;
    }
}
