package br.com.vivoapi.queue;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vivoapi.service.RegistroCdrService;

@Component
public class Receptor {

	@Autowired
	private RegistroCdrService registroCdrService;

	@RabbitListener(queues = "registro-cdr-queue-inclusao")
	public void receberMensagem(byte[] mensagem) {
		registroCdrService.incluir(SerializationUtils.deserialize(mensagem));
	}

	@RabbitListener(queues = "registro-cdr-queue-exclusao")
	public void receberMensagem(Long mensagem) {
		registroCdrService.excluir(mensagem);
	}
}
