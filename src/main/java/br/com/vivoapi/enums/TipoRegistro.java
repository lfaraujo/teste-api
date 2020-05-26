package br.com.vivoapi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoRegistro {

	VOZ(0, "Voz"), DADOS(1, "Dados"), SMS(2, "SMS");

	private Integer codigo;

	private String descricao;

}
