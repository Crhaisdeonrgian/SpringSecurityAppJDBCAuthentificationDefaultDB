package com.chidorirasengan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Creates FilterChain
 * See {@link org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration}
 * class' method springSecurityFilterChain()
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 * @version 10000.0))
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                //.usersByUsernameQuery("select email, password, enabled from customers where email=?")
                //.authoritiesByUsernameQuery("select email, roles from customers where email = ?")
                .passwordEncoder(bcryptPasswordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello","admin")
                    .authenticated()
                .antMatchers("/admin")
                    .hasAuthority("ADMIN")
                .antMatchers("/")
                    .permitAll()
                .and()
                .formLogin()
                    .loginPage("/loginpage")
                        .loginProcessingUrl("/process-login")
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
