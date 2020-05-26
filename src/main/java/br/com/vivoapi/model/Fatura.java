package br.com.vivoapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "fatura")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fatura implements Serializable {

	private static final long serialVersionUID = 917492813583833032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private LocalDate mesReferencia;

	@Column(nullable = false)
	private LocalDateTime periodoInicio;

	@Column(nullable = false)
	private LocalDateTime periodoFim;

	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(insertable = false)
	private LocalDateTime dataEmissao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produto produto;

}
