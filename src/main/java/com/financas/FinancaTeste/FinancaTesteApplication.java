package com.financas.FinancaTeste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FinancaTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancaTesteApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("123456"));
	}

}
