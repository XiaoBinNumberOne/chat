package com.xbim.config;

import com.xbim.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {


    /**
     * 数据源
     */
    @Resource
    private DataSource dataSource;


    /**
     * userDetailsServiceImpl 实现 UserDetailsService 接口，获取用户信息
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    /**
     * 查询自定义数据表加载客户端信息
     */
    @Autowired
    private ClientInfoConfigurer clientInfoConfigurer;

    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //开放oauth2的授权端点
        //允许表单认证
        security.allowFormAuthenticationForClients();
        //允许check_token访问
        security.checkTokenAccess("permitAll()");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 没有它，在使用refresh_token的时候会报错 IllegalStateException, UserDetailsService is required.
        endpoints.userDetailsService(userDetailsServiceImpl)
                //来支持 password grant type
                .authenticationManager(authenticationManager)
                // 不加报错"method_not_allowed"
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        //使用自定义的ClientDetailsService
        clients.withClientDetails(clientInfoConfigurer);
    }

}
