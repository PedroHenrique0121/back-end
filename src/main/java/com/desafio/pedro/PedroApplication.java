package com.desafio.pedro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



//@ComponentScan({"entity.Cliente","controller.ClienteControler","repository.ClienteRepository"})
@SpringBootApplication
public class PedroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedroApplication.class, args);
	}

}
