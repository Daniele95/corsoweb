package com.benfante.examples.echoservices;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger.
 *
 * @author <a href="mailto:lucio.benfante@gmail.com">Lucio Benfante</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/v1.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Echo Example API")
                .description("An example for services, and echo for HTTP examples")
                .termsOfServiceUrl("http://www.benfante.com")
                .contact(new Contact("Benfante development support", "http://www.benfante.com", "lucio.benfante@gmail.com")).license("Benfante License")
                .licenseUrl("http://www.benfante.com").version("1.0").build();
    }
}
