package com.xbim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbim.entity.TUser;
import com.xbim.mapper.TUserMapper;
import com.xbim.service.ITUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaobin
 * @since 2020-01-15
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
