package br.com.vivoapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "saldo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Saldo implements Serializable {

	private static final long serialVersionUID = -5705967208075818196L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column
	private Long produto;

	@Column
	private Integer saldoDados;

	@Column
	private Integer saldoMinutos;

	@Column
	private Integer saldoSms;

	@Column
	private LocalDateTime dataAtualizacao;

}
