package com.xbim.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
//    /**
//     * TUserService 实现 UserDetailsService 接口，获取用户信息
//     */
//    @Autowired
//    private TUserServiceImpl userService;

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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //使用UserDetailsService，密码使用的是BCryptPasswordEncoder加密
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }

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
