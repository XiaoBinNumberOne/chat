package com.xbim.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xiaobin
 * @date 2020/1/15 21:33
 * @desc 加载客户端信息
 */
@Component
public class ClientInfoConfigurer implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret("xiaobin");
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
//        clientDetails.setScope(Arrays.asList("user"));
        return clientDetails;
    }
}
