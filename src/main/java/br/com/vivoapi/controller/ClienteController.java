package br.com.vivoapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivoapi.dto.ClienteDTO;
import br.com.vivoapi.model.Cliente;
import br.com.vivoapi.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void incluir(@RequestBody ClienteDTO clienteDTO) {
		clienteService.incluir(modelMapper.map(clienteDTO, Cliente.class));
	}

}
