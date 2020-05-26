package br.com.vivoapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private String cpf;

	private List<ProdutoDTO> produtos;
}
