package com.example.demo.config;


import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableWebSecurity
//开启全局注解处理
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
//    @PreAuthorize("hasRole('4')") 检查角色
//    @PreAuthorize("hasAuthority('4')")//不通过 检查权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);   //此行代码如果不注释掉，登录的时候会报密码错误

        //auth.inMemoryAuthentication().withUser("test").password(passwordEncoder().encode("123")).roles("user");
        //自定义实体类实现userDetails接口并从数据库查询出用户名密码权限三个
        //注意List<GrantedAuthority> 中加了ROLE_表示角色 其他表示权限，剩下的只是根据字符串匹配
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //不使用密码加密算法会抛出java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 通过passwordEncoder的实现类，实现加密算法
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        System.out.println("-=========httpSecurity----------");
        http.authorizeRequests()
                //这个请求放行，不进行认证
                .antMatchers("/index").permitAll()
                //配置路径及对应的资源权限，也可以使用@PreAuthorize("hasRole('user')") @Secure
//                .antMatchers("/testauth").hasRole("user")
                .anyRequest().authenticated()   //其他请求需要认证
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll();
                //配置自己的登录页，如果只配置了loginPage部分，没有配置loginProcessingUrl，那么会出现无法获取到用户的问题，因为提交的地址并没有真正的提交到SpringSecurity
//                .loginPage("/myLogin.html").loginProcessingUrl("/login")
//                .defaultSuccessUrl("/testDemo/demo1").failureUrl("/login");


    }
}
