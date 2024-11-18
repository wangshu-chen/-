package com.bishe.teamproject.service.Impl;

import com.bishe.teamproject.model.ApplyInfo;
import com.bishe.teamproject.dao.ApplyInfoMapper;
import com.bishe.teamproject.model.ApplyList;
import com.bishe.teamproject.service.IApplyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;

/**
 * <p>
 * 社团入团申请记录,申请生成可入团 服务实现类
 * </p>
 *
 * @author chen
 * @since 2022-10-31
 */
@Service
public class ApplyInfoServiceImpl extends ServiceImpl<ApplyInfoMapper, ApplyInfo> implements IApplyInfoService {

    @Autowired
    private ApplyInfoMapper applyDao;

    @Override
    public  IPage<ApplyInfo> findListByPage(Integer page, Integer pageCount){
        IPage<ApplyInfo> wherePage = new Page<>(page, pageCount);
        ApplyInfo where = new ApplyInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(ApplyInfo applyInfo){
        return baseMapper.insert(applyInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(ApplyInfo applyInfo){
        return baseMapper.updateById(applyInfo);
    }

    @Override
    public ApplyInfo findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<ApplyInfo> queryAppInfoByPage(int page, int limit, ApplyInfo applyInfo) {
        PageHelper.startPage(page,limit);
        List<ApplyInfo> list = applyDao.queryAppList(applyInfo);
        PageInfo<ApplyInfo> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<ApplyList> queryAppListByAppId(Integer appId) {
        return applyDao.queryAppListByAppId(appId);
    }

    @Override
    public boolean updateStatus(ApplyInfo info) {
        return applyDao.updateStatus(info);
    }

    @Override
    public int insertAppList(ApplyList applyList) {
        return applyDao.insertAppList(applyList);
    }

    @Override
    public ApplyInfo queryApplyInfoByTeamIdAndTel(int teamId, String tel) {
        return applyDao.queryApplyInfoByTeamIdAndTel(teamId,tel);
    }

}
