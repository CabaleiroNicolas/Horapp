package com.horapp.config.documentation;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión Horarios con HorApp")
                        .version("1.0.0")
                        .description("La API de Gestión de HorApp proporciona un conjunto de endpoints diseñados para administrar todas las operaciones relacionadas a la aplicación HorApp."));
//                .addSecurityItem(new SecurityRequirement().addList("JWT"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("JWT", new SecurityScheme()
//                                .name("Authorization")
//                                .description("Formato: Bearer-**_pegar_jwt_token_aca_**")
//                                .type(SecurityScheme.Type.APIKEY)
//                                .in(SecurityScheme.In.HEADER)
//                        ))


    }
}
