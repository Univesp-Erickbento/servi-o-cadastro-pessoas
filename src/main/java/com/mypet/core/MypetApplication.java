package com.mypet.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.mypet.core.pessoa.adapter.out.persistence",
		"com.mypet.core.cliente.adapter.out.persistence",
		"com.mypet.core.adapters.out.repositories" // <-- Funcionario
})
@EntityScan(basePackages = {
		"com.mypet.core.pessoa.adapter.out.persistence.entity",
		"com.mypet.core.cliente.adapter.out.persistence.entity",
		"com.mypet.core.application.core.domain.model" // <-- Funcionario
})
public class MypetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypetApplication.class, args);
	}
}
