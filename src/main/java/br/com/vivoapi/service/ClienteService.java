package br.com.vivoapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.dto.ClienteDTO;
import br.com.vivoapi.model.Cliente;
import br.com.vivoapi.model.NumeroChip;
import br.com.vivoapi.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private NumeroChipService numeroChipService;

	public Cliente incluir(ClienteDTO clienteDTO) {

		numeroChipService.validarExistenciaNumero(clienteDTO.getNumero());

		Optional<Cliente> clienteOptional = clienteRepository.findByCpf(clienteDTO.getCpf());
		
		Cliente cliente;

		if (clienteOptional.isPresent()) {
			cliente = clienteOptional.get();
		} else {
			cliente = new Cliente();
			cliente.setCpf(clienteDTO.getCpf());
		}
		
		NumeroChip numeroChip = new NumeroChip();
		numeroChip.setNumero(clienteDTO.getNumero());
		cliente.getNumeros().add(numeroChip);
		return clienteRepository.save(cliente);
	}

}
