package com.yedam.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
	@Value("${file.upload.path}") // 환경변수 및 Properties 파일의 변수 값을 Read
	private String uploadPath;
	// 리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imgs/**").addResourceLocations("file:///" + uploadPath); // 두개 한쌍 URL, 실제경로
	}
	
}
