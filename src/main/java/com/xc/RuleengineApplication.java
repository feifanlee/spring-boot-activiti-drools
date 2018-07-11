package com.xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication/*(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})*/
@ComponentScan
@EnableAutoConfiguration(exclude = {
		/*关闭Spring boot Sercurity
		org.activiti.spring.boot.RestApiAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class*/
		// org.activiti.spring.boot.SecurityAutoConfiguration.class,  ACTIVITI 5.17.0 以上版本
		org.activiti.spring.boot.RestApiAutoConfiguration.class  // ACTIVITI 5.17.0
})
@EnableCaching
public class RuleengineApplication {
	public static void main(String[] args) {
		SpringApplication.run(RuleengineApplication.class, args);


	}

}
