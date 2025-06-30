package br;

import org.springframework.boot.SpringApplication;

public class TestPraFrentexBankingApplication {

	public static void main(String[] args) {
		SpringApplication.from(PraFrentexBankingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
