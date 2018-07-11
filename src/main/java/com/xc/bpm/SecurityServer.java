//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xc.bpm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.security.BasicAuthenticationProvider;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@AutoConfigureAfter({SecurityAutoConfiguration.class})
@ConditionalOnClass(
        name = {"org.activiti.rest.service.api.RestUrls", "org.springframework.web.servlet.DispatcherServlet"}
)
public class SecurityServer {
    public SecurityServer() {
    }

    @Bean
    public RestResponseFactory restResponseFactory() {
        RestResponseFactory restResponseFactory = new RestResponseFactory();
        return restResponseFactory;
    }

    @Bean
    public ContentTypeResolver contentTypeResolver() {
        ContentTypeResolver resolver = new DefaultContentTypeResolver();
        return resolver;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Configuration
    @ConditionalOnClass(
            name = {"org.activiti.rest.service.api.RestUrls", "org.springframework.web.servlet.DispatcherServlet"}
    )
    @EnableWebSecurity
    @EnableWebMvcSecurity
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        public SecurityConfiguration() {
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            return new BasicAuthenticationProvider();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            ((HttpSecurity)((AuthorizedUrl)((HttpSecurity)http
                    .authenticationProvider(this.authenticationProvider())
                    .csrf().disable())
                    .authorizeRequests()
                    .antMatchers("/login?error","/names","/register*","/js/**","/css/**","fonts/**","/images/**","/vendor*/**").permitAll()
                    .antMatchers("/event").permitAll()
                    .anyRequest()).authenticated().and())
                    .httpBasic();
        }
    }

    @Configuration
    @ComponentScan({"org.activiti.rest.exception", "org.activiti.rest.service.api"})
    public static class ComponentScanRestResourcesConfiguration {
        public ComponentScanRestResourcesConfiguration() {
        }
    }
}
