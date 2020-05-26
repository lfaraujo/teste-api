package br.com.vivoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.model.Produto;
import br.com.vivoapi.model.Saldo;
import br.com.vivoapi.repository.ProdutoRepository;
import br.com.vivoapi.repository.SaldoRepository;

@Service
public class SaldoService {

	@Autowired
	private SaldoRepository saldoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Saldo consultar(String numero) {
		Produto produto = null;
		Saldo saldo = null;
		
		produto = produtoRepository.findByNumero(numero);
		
		if (produto != null) {
			saldo = saldoRepository.findByProduto(produto.getId());
		}

		return saldo;
	}

}
