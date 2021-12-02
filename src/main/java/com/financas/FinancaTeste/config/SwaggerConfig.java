package com.financas.FinancaTeste.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI registroFinancaApi() {
        return new OpenAPI().info(new Info().title("Api para registros de gastos")
                .description("Api para registro de gastos de produtos, contas e serviços"+
                        " Grupo: Amauri Carvalho do Espirito Santo, Filipe Gama de Souza, Marcos de Jesus OLiveira")
                .version("v.0.1")
                .contact(new Contact()
                        .name("Marcos de Jesus Oliveira")
                        .email("marcos.oliveira135@fatec.sp.gov.br")
                        )
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }
}
