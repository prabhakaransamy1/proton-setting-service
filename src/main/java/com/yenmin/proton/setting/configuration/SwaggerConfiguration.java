package com.yenmin.proton.setting.configuration;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

/**
 * This class is used for creating the swagger document for the notification
 * module
 **/

public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		Parameter authHeader = new ParameterBuilder().parameterType("header").name("Authorization")
				.modelRef(new ModelRef("string")).defaultValue("Bearer").required(true).build();

		SecurityReference securityReference = SecurityReference.builder().reference("basicAuth")
				.scopes(new AuthorizationScope[0]).build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("basicAuth"));

		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(auth).securityContexts(securityContexts).select()
				.apis(RequestHandlerSelectors.basePackage("com.yenmin.proton.setting.controller"))
				.paths(PathSelectors.ant("/**")).build()
				.globalOperationParameters(Collections.singletonList(authHeader)).apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Productivity Tracker Rest APIs")
				.description("This lists all the rest apis for Productivity Tracker Application.")
				.version("1.0-SNAPSHOT").build();
	}

}
