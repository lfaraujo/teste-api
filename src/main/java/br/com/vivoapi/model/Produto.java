package br.com.vivoapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = -7905990218308032131L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(unique = true, nullable = false)
	private String numero;

	@Column(nullable = false)
	private Integer qtdDados;

	@Column(nullable = false)
	private Integer qtdSms;

	@Column(nullable = false)
	private Integer qtdMinutos;

	@Column(insertable = false)
	private LocalDateTime dataHabilitacao;

	@ManyToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;

}
