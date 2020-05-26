package br.com.vivoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.model.Cliente;
import br.com.vivoapi.model.Produto;
import br.com.vivoapi.repository.ClienteRepository;
import br.com.vivoapi.repository.ProdutoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public void incluir(Cliente cliente) {
		Cliente clienteCriado = clienteRepository.save(cliente);

		List<Produto> produtos = cliente.getProdutos();

		produtos.forEach(p -> {
			p.setCliente(clienteCriado);
			produtoRepository.save(p);
		});
	}

}
