package fr.jouhs;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.jouhs.entities.AppRole;
import fr.jouhs.service.AccountService;

@SpringBootApplication
public class SecServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.save(new AppRole(null, "USER"));
			accountService.save(new AppRole(null, "ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(user -> {
				accountService.saveUser(user, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}

