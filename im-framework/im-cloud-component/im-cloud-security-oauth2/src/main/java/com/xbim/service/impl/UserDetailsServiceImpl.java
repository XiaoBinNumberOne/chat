package com.xbim.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xbim.entity.TUser;
import com.xbim.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TUserMapper tUserMapper;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        TUser tUser = tUserMapper.selectOne(wrapper);
        if (ObjectUtil.isEmpty(tUser)) {
            throw new UsernameNotFoundException("未找到您的账户信息");
        }
        return tUser;
    }
}
