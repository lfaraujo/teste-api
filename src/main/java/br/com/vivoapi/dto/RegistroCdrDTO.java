package br.com.vivoapi.dto;

import java.io.Serializable;

import br.com.vivoapi.enums.TipoRegistro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroCdrDTO implements Serializable {

	private static final long serialVersionUID = -8986030303645988439L;

	private String origem;

	private String destino;

	private TipoRegistro tipoRegistro;

	private Long consumo;

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = TipoRegistro.valueOf(tipoRegistro.toUpperCase());
	}

}
