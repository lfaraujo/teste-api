package br.com.vivoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivoapi.dto.ClienteDTO;
import br.com.vivoapi.model.Cliente;
import br.com.vivoapi.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public Cliente incluir(@RequestBody ClienteDTO clienteDTO) {
		return clienteService.incluir(clienteDTO);
	}
	
}