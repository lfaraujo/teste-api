package br.com.vivoapi;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class VivoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivoApiApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new Queue("registro-cdr-queue", true);
	}

}
