package com.chidorirasengan.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * Firstly, this {@link SecurityInitializer} class is created
 * Secondly, it looks for filters
 * Thirdly, it registers FilterChain from {@link SecurityConfig}
 * In other words {@link SecurityInitializer} boots up our Security App
 * See {@link AbstractSecurityWebApplicationInitializer} onStartup() method
 * So we've done next steps:
 * request=>[DelegatingFilterProxy]->[FilterChainProxy]->[SecurityFilterChain]->[DispatcherServlet]
 *
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 * @version 10000.0))
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
