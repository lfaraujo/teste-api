package br.com.vivoapi;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRabbit
@EnableSwagger2
public class VivoApiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(VivoApiApplication.class, args);
	}

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("registro_cdr.topic");
	}

	@Bean
	public Queue filaInclusao() {
		return new Queue("registro-cdr-queue-inclusao", false);
	}

	@Bean
	public Queue filaExclusao() {
		return new Queue("registro-cdr-queue-exclusao", false);
	}

	@Bean
	public Binding bindingInclusao(TopicExchange topic, Queue filaInclusao) {
		return BindingBuilder.bind(filaInclusao).to(topic).with("*.incluir");
	}

	@Bean
	public Binding bindingExclusao(TopicExchange topic, Queue filaExclusao) {
		return BindingBuilder.bind(filaExclusao).to(topic).with("*.excluir");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
