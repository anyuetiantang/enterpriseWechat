package com.enterpriseWechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
//	@Bean
//	public HttpClientEnhanceConfig httpClientEnhanceConfig() {
//		HttpClientEnhanceConfig config = new HttpClientEnhanceConfig();
//		config.setMaxTotal(200);
//		config.setDefaultMaxPerRoute(100);
//		config.setConnectTimeout(3000);
//		config.setReadTimeout(3000);
//		config.setIsUsePool(true);
//		config.setRequestContentEncoding("UTF-8");
//		config.setRetryRequestCount(3);
//		
//		return config;
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
