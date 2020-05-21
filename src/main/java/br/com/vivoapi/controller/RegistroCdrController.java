package br.com.vivoapi.controller;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivoapi.dto.RegistroCdrDTO;
import br.com.vivoapi.service.RegistroCdrService;

@RestController
@RequestMapping("/registros")
public class RegistroCdrController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	@Autowired
	RegistroCdrService registroCdrService;

	@PostMapping
	public void incluir(@RequestBody RegistroCdrDTO registroCdrDTO) {
		byte[] dadosMensagem = SerializationUtils.serialize(registroCdrDTO);
		rabbitTemplate.convertAndSend(this.queue.getName(), dadosMensagem);
	}

}
