package com.yuhannci.erp;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yuhannci.erp.controller.UrlInterceptor;

@Configuration
public class DefaultPageConfig extends WebMvcConfigurerAdapter{

	// 기본 웹 페이지 설정
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/login.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
   @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new UrlInterceptor()).addPathPatterns("/**");
    }
}
