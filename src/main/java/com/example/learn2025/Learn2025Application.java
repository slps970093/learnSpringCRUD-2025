package com.example.learn2025;

import com.example.learn2025.entities.Member;
import com.example.learn2025.repositories.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Learn2025Application {

	public static void main(String[] args) {
		SpringApplication.run(Learn2025Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MemberRepository repository) {
		return args -> {
			var member = Member.builder()
					.firstName("Yu-Hsien")
					.lastName("Hsien")
					.account("test")
					.password("passwd")
					.name("monkey")
					.build();
			repository.save(member);
		};
	}
}
