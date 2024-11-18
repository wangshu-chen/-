package com.bishe.teamproject.service.Impl;

import com.bishe.teamproject.model.Activity;
import com.bishe.teamproject.dao.ActivityMapper;
import com.bishe.teamproject.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.teamproject.util.R;
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
 * 社团活动管理 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private  ActivityMapper activityDao;

    @Override
    public  IPage<Activity> findListByPage(Integer page, Integer pageCount){
        IPage<Activity> wherePage = new Page<>(page, pageCount);
        Activity where = new Activity();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Activity activity){
        return baseMapper.insert(activity);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Activity activity){
        return baseMapper.updateById(activity);
    }

    @Override
    public Activity findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<Activity> queryList(int page, int limit, String name, String tel, Integer userId) {
        PageHelper.startPage(page,limit);
        List<Activity> activities = activityDao.queryActivityInfoList(name, tel, userId);
        PageInfo<Activity> pageInfo = new PageInfo(activities);
        return pageInfo;
    }

    @Override
    public boolean updateStatus(int status, int id) {
        int num =  activityDao.updateStatusById(status,id);
        if (num>0){
            return true;
        }
        return false;
    }

    @Override
    public Activity findByTeamId(Integer teamId) {
        return activityDao.findByTeamId(teamId);
    }


}
