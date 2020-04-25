package com.javainuse.swaggertest;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final String SOURCE_TYPE = "SOURCE_TYPE";

	@Bean
	public Docket publicApi() {
		List<Parameter> params = new ArrayList<>();
		params.add(buildPathParam(SOURCE_TYPE, "source1", Arrays.asList("source1", "source2")));
		params.add(buildHeaderParam("X-TENANT-ID", "tesnant1"));
		params.add(buildHeaderParam("X-CLIENT-ID", "MyApp1"));
		params.add(buildHeaderParam("Content-Type", "application/json"));

		Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("@public-api").select()
				.apis(RequestHandlerSelectors.basePackage("com.javainuse.swaggertest.external"))
				.paths(PathSelectors.ant("/**"))
				.build().globalOperationParameters(params)
				.apiInfo(apiInfo()).select().build()
				;

		docket.globalResponseMessage(RequestMethod.POST, getDefaultResponses())
				.globalResponseMessage(RequestMethod.PUT, getDefaultResponses())
				.globalResponseMessage(RequestMethod.GET, getDefaultResponses())
				.globalResponseMessage(RequestMethod.DELETE, getDefaultResponses())
				.securityContexts(Arrays.asList(actuatorSecurityContext()))
				.securitySchemes(Arrays.asList(basicAuthScheme()));




		return docket;
	}

	private SecurityContext actuatorSecurityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(basicAuthReference()))
				.forPaths(PathSelectors.ant("/**"))
				.build();
	}

	private SecurityScheme basicAuthScheme() {
		return new BasicAuth("basicAuth");
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}


//
//	@Bean
//	SecurityConfiguration security() {
//		return SecurityConfigurationBuilder.builder().useBasicAuthenticationWithAccessCodeGrant(true).build();
//	}
//
//	private ApiKey apiKey() {
//		return new ApiKey("Authorization", "Authorization", "header");
//	}
//
////	private SecurityContext securityContext() {
////		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/*"))
////				.build();
////	}
//
////	List<SecurityReference> defaultAuth() {
////		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////		authorizationScopes[0] = authorizationScope;
////		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
////	}
////
//	private SecurityContext securityContext() {
//		return SecurityContext.builder()
//				.securityReferences(Arrays.asList(basicAuthReference()))
//				.forPaths(PathSelectors.ant("/api/v1/**"))
//				.build();
//	}
//
//	private SecurityScheme basicAuthScheme() {
//		return new BasicAuth("basicAuth");
//	}
//
//	private SecurityReference basicAuthReference() {
//		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//	}





	@Bean
	public Docket internalAPI() {
		List<Parameter> params = new ArrayList<>();
		params.add(buildPathParam(SOURCE_TYPE, "source1", Arrays.asList("source1", "source2")));
		params.add(buildHeaderParam("X-TENANT-ID", "tesnant1"));
		params.add(buildHeaderParam("X-CLIENT-ID", "MyApp1"));
		params.add(buildHeaderParam("Content-Type", "application/json"));

		ApiInfo apiInfo = new ApiInfoBuilder().title("Internal API")
				.description("Sample documentation for internal developers")
				.termsOfServiceUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.license("Apache 2.0")
				.licenseUrl("test@aa.com").version("1.0").build();

		Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("internal-api").select()
				.paths(internalPaths())
				.build().globalOperationParameters(params)
				.apiInfo(apiInfo).select().build()
				;
		docket.globalResponseMessage(RequestMethod.POST, getDefaultResponses())
				.globalResponseMessage(RequestMethod.PUT, getDefaultResponses())
				.globalResponseMessage(RequestMethod.GET, getDefaultResponses())
				.globalResponseMessage(RequestMethod.DELETE, getDefaultResponses());

		return docket;
	}

	private Predicate<String> internalPaths() {
		return or(regex("/internal-apis/.*"));
	}

	private List<ResponseMessage> getDefaultResponses() {
		//TODO investigate on how to integrate ModelRef
//		ModelRef errorModel = new ModelRef("errorInfo");
//
//		List<ResponseMessage> responseMessages = Arrays.asList(
//				new ResponseMessageBuilder().code(401).message("Unauthorized").responseModel(errorModel).build(),
//				new ResponseMessageBuilder().code(403).message("Forbidden").responseModel(errorModel).build(),
//				new ResponseMessageBuilder().code(404).message("NotFound").responseModel(errorModel).build());


		List<ResponseMessage> responseMessages = Arrays.asList(
				new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
				new ResponseMessageBuilder().code(403).message("Forbidden").build(),
				new ResponseMessageBuilder().code(404).message("NotFound").build());

		return responseMessages;
	}


	private Parameter buildHeaderParam(String name, String value) {
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name(name)
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.defaultValue(value)
				.required(true);
		return parameterBuilder.build();
	}

	private Parameter buildPathParam(String name, String value, List<String> allowedValues) {
		AllowableListValues values = new AllowableListValues(allowedValues, "path");
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name(name)
				.modelRef(new ModelRef("string"))
				.parameterType("path")
				.defaultValue(value)
				.allowableValues(values)
				.required(true);
		return parameterBuilder.build();
	}



	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Simple Swagger API")
				.description("Sample documentation for springboot developers")
				.termsOfServiceUrl("http://javainuse.com")
				.license("JavaInUse License")
				.licenseUrl("javainuse@gmail.com").version("1.0").build();
	}

}