package com.yuhannci.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableConfigurationProperties(EmailConfig.class)
@EnableCaching
public class YuhanerpWebApplication extends SpringBootServletInitializer {
	
	@Autowired YerpConfig yerpConfig;
	
	public static void main(String[] args) {
		SpringApplication.run(YuhanerpWebApplication.class, args);
	}
	 
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	     return application.sources(YuhanerpWebApplication.class);
	 }
	
/*
	@Bean
	public ErrorPageFilter errorPageFilter() {
		return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

*/


	
}
