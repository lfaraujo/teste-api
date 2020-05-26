package br.com.vivoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivoapi.dto.RegistroCdrDTO;
import br.com.vivoapi.model.Produto;
import br.com.vivoapi.model.RegistroCdr;
import br.com.vivoapi.repository.ProdutoRepository;
import br.com.vivoapi.repository.RegistroCdrRepository;

@Service
public class RegistroCdrService {

	@Autowired
	RegistroCdrRepository registroCdrRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	public void incluir(RegistroCdrDTO registroCdrDTO) {
		Produto produto = produtoRepository.findByNumero(registroCdrDTO.getOrigem());

		if (produto != null) {
			RegistroCdr registroCdr = new RegistroCdr();
			registroCdr.setOrigem(produto.getId());
			registroCdr.setDestino(registroCdrDTO.getDestino());
			registroCdr.setTipoRegistro(registroCdrDTO.getTipoRegistro());
			registroCdr.setConsumo(registroCdrDTO.getConsumo());

			registroCdrRepository.save(registroCdr);
		}
	}

	public void excluir(Long id) {
		if (buscar(id).isPresent()) {
			registroCdrRepository.deleteById(id);
		}
	}

	public Optional<RegistroCdr> buscar(Long id) {
		return registroCdrRepository.findById(id);
	}

	public List<RegistroCdr> consultarExtrato(String numero) {
		return registroCdrRepository.findByOrigemAndTipoRegistro(numero);
	}

}
