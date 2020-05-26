package br.com.vivoapi.controller;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivoapi.dto.RegistroCdrDTO;
import br.com.vivoapi.service.RegistroCdrService;

@RestController
@RequestMapping("/registros")
public class RegistroCdrController {

	@Autowired
	RegistroCdrService registroCdrService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private TopicExchange topic;

	@PostMapping
	public void incluir(@RequestBody RegistroCdrDTO registroCdrDTO) {

		byte[] conteudoMensagem = SerializationUtils.serialize(registroCdrDTO);

		rabbitTemplate.convertAndSend(topic.getName(), "registrocdr.incluir", conteudoMensagem);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {

		rabbitTemplate.convertAndSend(topic.getName(), "registrocdr.excluir", id);
	}

}
