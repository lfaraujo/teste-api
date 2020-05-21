package br.com.vivoapi.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroCdrDTO implements Serializable {

	private static final long serialVersionUID = -8986030303645988439L;

	private String numero;

	private Integer tipoCdr;

	private Long consumo;

	private Long valor;

}
