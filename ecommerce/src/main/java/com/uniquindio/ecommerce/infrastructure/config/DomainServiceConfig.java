package com.uniquindio.ecommerce.infrastructure.config;

import com.uniquindio.ecommerce.domain.Service.VerificadorDeCompra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {

    @Bean
    public VerificadorDeCompra verificadorDeCompra() {
        return new VerificadorDeCompra();
    }
}