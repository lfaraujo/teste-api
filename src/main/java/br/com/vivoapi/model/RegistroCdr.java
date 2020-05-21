package br.com.vivoapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vivoapi.enums.TipoCdr;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registro_cdr")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroCdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_numero_chip")
	private NumeroChip numero;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_cdr")
	private TipoCdr tipoCdr;

	private Long consumo;

	private Long valor;

	private LocalDateTime dataInclusao;

}
