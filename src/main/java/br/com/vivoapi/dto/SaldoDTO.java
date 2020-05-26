package br.com.vivoapi.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaldoDTO implements Serializable {

	private static final long serialVersionUID = -4069309540798050616L;

	private String numero;

	private Integer saldoVoz;

	private Integer saldoDados;

	private Integer saldoSms;

}
