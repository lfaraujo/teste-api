package br.com.vivoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.model.Fatura;
import br.com.vivoapi.repository.FaturaRepository;

@Service
public class FaturaService {

	@Autowired
	private FaturaRepository faturaRepository;

	public List<Fatura> consultarFaturas(String numero) {
		return faturaRepository.buscarFaturasPorNumero(numero);
	}

}
