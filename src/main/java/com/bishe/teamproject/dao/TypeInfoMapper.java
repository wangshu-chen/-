package com.bishe.teamproject.dao;

import com.bishe.teamproject.model.TypeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 社团分类和介绍 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Component("typeDao")
public interface TypeInfoMapper extends BaseMapper<TypeInfo> {

    List<TypeInfo> queryTypeList(TypeInfo info);

}
