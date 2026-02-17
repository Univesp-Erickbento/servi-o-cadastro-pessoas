package com.mypet.mypet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.mypet.mypet.domain.model")
@SpringBootApplication
public class MypetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypetApplication.class, args);

	}
}
