package com.heilash.logistoffer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application.api-info")
public class SwaggerProperties {

    private String version;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private Contact contact;

    @Getter
    @Setter
    public static class Contact {

        private String name;
        private String url;
        private String email;
    }
}