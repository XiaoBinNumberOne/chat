package com.xbim.config;


import com.xbim.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

/**
 * @ClassName WebSecurityConfig
 * @DeScription TODO
 * @Author
 * @Date 2019/4/1  17:33
 * @Version 1.0
 **/


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    /**
     * userDetailsServiceImpl 实现 UserDetailsService 接口，获取用户信息
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 将AuthenticationManager注入到Spring容器中，认证服务器配置需要用到
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用UserDetailsService，密码使用的是BCryptPasswordEncoder加密
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 使用 Spring Security 加密方式BCryptPasswordEncoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(), s);
            }
        };
    }


    /**
     * security的拦截路径，使用表单认证，并且拦截所有请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .authorizeRequests().anyRequest().authenticated();
        // Security的默认拦截器里，默认会开启CSRF处理，判断请求是否携带了token
        http.csrf().disable();
    }


}
