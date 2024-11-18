package com.bishe.teamproject.service.Impl;

import com.bishe.teamproject.model.CostList;
import com.bishe.teamproject.dao.CostListMapper;
import com.bishe.teamproject.service.ICostListService;
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
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Service
public class CostListServiceImpl extends ServiceImpl<CostListMapper, CostList> implements ICostListService {

    @Autowired
    private CostListMapper costListDao;

    @Override
    public  IPage<CostList> findListByPage(Integer page, Integer pageCount){
        IPage<CostList> wherePage = new Page<>(page, pageCount);
        CostList where = new CostList();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(CostList costList){
        return baseMapper.insert(costList);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(CostList costList){
        return baseMapper.updateById(costList);
    }

    @Override
    public CostList findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<CostList> queryCostListAll(String name, Integer teamId, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<CostList> costLists = costListDao.queryCostListAll(name, teamId);
        PageInfo<CostList> pageinfo = new PageInfo<>(costLists);
        return pageinfo;
    }
}
