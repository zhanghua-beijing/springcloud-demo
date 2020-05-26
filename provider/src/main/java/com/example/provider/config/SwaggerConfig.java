package com.example.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		// 文档类型
		return new Docket(DocumentationType.SWAGGER_2)
				// 创建api的基本信息
				.apiInfo(apiInfo())
				// 选择哪些接口去暴露
				.select()
				// 扫描的包
				.apis(RequestHandlerSelectors.basePackage("com.example.provider.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springcloud-demo接口文档")
				.contact(new Contact("zhanghua","null","zhanghua9527@qq.com"))
				.version("1.0")
				.build();
	}
}
