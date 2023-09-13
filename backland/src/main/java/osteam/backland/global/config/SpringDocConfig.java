package osteam.backland.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVesion) {
        Info info = new Info()
                .title("웹 랜딩 서버")
                .version(springdocVesion)
                .description("랜딩 화이팅");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
