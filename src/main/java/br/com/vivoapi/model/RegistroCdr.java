package br.com.vivoapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.vivoapi.enums.TipoRegistro;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registro_cdr")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroCdr implements Serializable {

	private static final long serialVersionUID = -3679711232684823862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private Long origem;

	private String destino;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private TipoRegistro tipoRegistro;

	@Column(nullable = false)
	private Long consumo;

	@Column(insertable = false)
	private LocalDateTime dataInclusao;

}
