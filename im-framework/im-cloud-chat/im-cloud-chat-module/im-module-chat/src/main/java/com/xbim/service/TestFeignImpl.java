package com.xbim.service;

import com.xbim.chat.service.TestFeign;
import org.springframework.stereotype.Service;

/**
 * @author xiaobin
 * @date 2020/2/10 13:42
 * @desc
 */
@Service
public class TestFeignImpl implements TestFeign {

    @Override
    public String test() {
        return "hello";
    }
}
