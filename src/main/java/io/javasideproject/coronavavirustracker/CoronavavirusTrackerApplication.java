 package io.javasideproject.coronavavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavavirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavavirusTrackerApplication.class, args);
	}

}
