package com.bishe.teamproject.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.teamproject.model.UserInfo;
import org.springframework.stereotype.Component;

@Component("userDao")
public interface UserMapper extends BaseMapper<UserInfo> {
}
