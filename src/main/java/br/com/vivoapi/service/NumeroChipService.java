package br.com.vivoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.repository.NumeroChipRepository;

@Service
public class NumeroChipService {

	@Autowired
	private NumeroChipRepository numeroChipRepository;

	public void validarExistenciaNumero(String numero) {

		if (numeroChipRepository.findByNumero(numero).isPresent()) {
			throw new RuntimeException();
		}
	}

}
