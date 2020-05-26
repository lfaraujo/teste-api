package br.com.vivoapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 7544859679522680347L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Transient
	private List<Produto> produtos;

}
