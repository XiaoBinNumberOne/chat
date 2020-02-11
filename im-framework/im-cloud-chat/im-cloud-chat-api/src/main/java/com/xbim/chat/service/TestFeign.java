package com.xbim.chat.service;


import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xiaobin
 * @date 2020/2/10 13:28
 * @desc
 */
@FeignClient("testFeign")
public interface TestFeign {

    String test();

}
