package com.heilash.logistoffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static com.heilash.logistoffer.config.Constants.Headers.X_LOCALE;
import static com.heilash.logistoffer.config.Constants.Service.BASIC_AUTH;
import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
@PropertySource("swagger-api.properties")
public class SwaggerConfig {

    @Resource
    private SwaggerProperties apiProperties;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.heilash.logistoffer."))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo())
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name(X_LOCALE)
                                .description("Locale header")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))
                .securitySchemes(createSecuritySchemes());
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo(
                apiProperties.getTitle(),
                apiProperties.getDescription(),
                apiProperties.getVersion(),
                apiProperties.getTermsOfServiceUrl(),
                createContact(apiProperties.getContact()),
                apiProperties.getLicense(),
                apiProperties.getLicenseUrl(),
                Collections.emptyList()
        );
    }

    private Contact createContact(SwaggerProperties.Contact contact) {
        return new Contact(
                contact.getName(),
                contact.getUrl(),
                contact.getEmail()
        );
    }

    private List<SecurityScheme> createSecuritySchemes() {
        return singletonList(new BasicAuth(BASIC_AUTH));
    }
}