package kr.omen.pico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicoApplication.class, args);
	}

}
