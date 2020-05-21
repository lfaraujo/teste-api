package br.com.vivoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.dto.RegistroCdrDTO;
import br.com.vivoapi.model.NumeroChip;
import br.com.vivoapi.model.RegistroCdr;
import br.com.vivoapi.repository.RegistroCdrRepository;

@Service
public class RegistroCdrService {

	@Autowired
	RegistroCdrRepository registroCdrRepository;

	public void incluir(RegistroCdrDTO registroCdrDTO) {

		NumeroChip numeroChip = new NumeroChip();
		numeroChip.setNumero(registroCdrDTO.getNumero());

		RegistroCdr registroCdr = new RegistroCdr();
		registroCdr.setNumero(numeroChip);
		
		registroCdrRepository.save(registroCdr);
	}

}
