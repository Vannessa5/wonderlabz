package za.co.wonderbank.configs;

import io.jsonwebtoken.lang.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;

// would love to implements this.
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return null;
    }

    private ApiInfo apiInfo() {
        return null;
    }

    private Contact contact() {
        return null;
    }
}
