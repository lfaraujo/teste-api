package br.com.vivoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.RegistroCdr;

@Repository
public interface RegistroCdrRepository extends JpaRepository<RegistroCdr, Long> {

	@Query(value = "SELECT a.id, b.numero as origem, a.destino, a.tipo_registro, a.consumo, a.data_inclusao "
			+ "FROM registro_cdr AS a INNER JOIN produto AS b ON a.origem = b.id "
			+ "WHERE a.tipo_registro IN (0, 1) AND b.numero = ?1 ", nativeQuery = true)
	List<RegistroCdr> findByOrigemAndTipoRegistro(String origem);

}
