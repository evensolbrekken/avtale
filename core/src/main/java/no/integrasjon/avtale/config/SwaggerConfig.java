package no.integrasjon.avtale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        //noinspection Guava
        return new Docket(SWAGGER_2)
                .select()
                .apis(not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("AVTALER REST API")
                                .description("AVTALER REST API")
                                .build()
                );
    }
}
