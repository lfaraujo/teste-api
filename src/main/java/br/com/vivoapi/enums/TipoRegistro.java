package br.com.vivoapi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoRegistro {

	VOZ(0, "Voz"), DADOS_MOVEIS(1, "Dados Móveis"), SMS(2, "SMS");

	private Integer codigo;

	private String descricao;

}
