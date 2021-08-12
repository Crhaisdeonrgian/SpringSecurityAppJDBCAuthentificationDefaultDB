package com.chidorirasengan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;


/**
 * Creates resolver instance w/ preset prefix and suffix
 * Currently it resolves jsp files from views directory
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 * @version 10000.0))
 */
@Configuration
@ComponentScan("com.chidorirasengan")
@EnableWebMvc
public class SpringConfig {
    @Bean
    InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());

    }
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource());
        /*userDetailsManager.setUsersByUsernameQuery("select email, password, enabled from customers where email=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select email, roles from customers where email = ?");
        userDetailsManager.setChangePasswordSql("update customers set password=? where email = ?");
        userDetailsManager.setCreateUserSql("insert into customers (email, password, enabled) values (?,?,?)");
        userDetailsManager.setDeleteUserSql("delete from customers where email = ?");
        userDetailsManager.setCreateAuthoritySql("insert into customers where email = ? (roles) values (?)");*/
        return userDetailsManager;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/template_db");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("postgres");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}