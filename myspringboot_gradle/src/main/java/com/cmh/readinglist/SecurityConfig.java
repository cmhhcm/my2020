package com.cmh.readinglist;

//import com.cmh.readinglist.ReaderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Example;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
*
 * @author: meice Huang
 * @date: 2020/3/24 上午7:06

*/

//@Configuration
//@EnableWebSecurity  extends WebSecurityConfigurerAdapter
public class SecurityConfig {

    /*@Autowired
    private ReaderRepository readerRepository;*/

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
/**
         * 登录需要READER角色
         * 设置登录表单路径*/


//        http
//                .authorizeRequests()
//                .antMatchers("/").access("hasRole('READER')")
//                .antMatchers("/**").permitAll();

//                .and()

//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true");
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return readerRepository.findById(username).orElse(null);
            }
            //此处若使用findOne会因为springboot版本问题报错：Inferred type 'S' for type parameter 'S' is not within its bound; should extend 'com.cmh.readinglist.Reader'
        });*/
//    }
}
