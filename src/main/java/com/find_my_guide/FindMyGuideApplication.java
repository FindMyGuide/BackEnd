package com.find_my_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class FindMyGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindMyGuideApplication.class, args);
	}

}
