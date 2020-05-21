package br.com.vivoapi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoCdr {

	LIGACAO(1, "Ligação"), DADOS_MOVEIS(2, "Dados Móveis"), SMS(3, "SMS");

	@Getter
	private Integer codigo;

	@Getter
	private String descricao;

}
