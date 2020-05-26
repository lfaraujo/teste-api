package br.com.vivoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

	@Query(value = "SELECT a.id, a.mes_referencia, a.periodo_inicio, a.periodo_fim, a.valor, a.data_emissao "
			+ "FROM fatura AS a INNER JOIN produto AS b ON a.produto_id = b.id "
			+ "WHERE b.numero = ?1", nativeQuery = true)
	List<Fatura> buscarFaturasPorNumero(String numero);
	
}
