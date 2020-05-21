package br.com.vivoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "numero_chip")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NumeroChip {

	@Id
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(unique=true)
	private String numero;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

}
