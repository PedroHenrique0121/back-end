package com.desafio.pedro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PedroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedroApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/clientes").allowedOrigins("http://localhost:3000");
				registry.addMapping("/cadastrar").allowedOrigins("http://localhost:3000");
				registry.addMapping("/deletarCliente/{id}").allowedOrigins("http://localhost:3000")
						.allowedMethods("DELETE");
				registry.addMapping("/buscarPorNome/{nome}").allowedOrigins("http://localhost:3000");
				registry.addMapping("/alterarCliente/{id}").allowedOrigins("http://localhost:3000")
						.allowedMethods("PUT");
			}
		};
	}

}
