package br.com.vivoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.dto.SaldoDTO;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoDTO, Long> {
	
	@Query(value = "SELECT numero,\r\n" + 
			"       CASE\r\n" + 
			"           WHEN rc.tipo_registro = 0 AND p.qtd_minutos - rc.consumo IS NOT NULL\r\n" + 
			"               THEN p.qtd_minutos - rc.consumo\r\n" + 
			"           WHEN rc.tipo_registro = 0 AND p.qtd_minutos - rc.consumo < 0\r\n" + 
			"               THEN 0\r\n" + 
			"           ELSE p.qtd_minutos\r\n" + 
			"           END AS saldo_voz,\r\n" + 
			"       CASE\r\n" + 
			"           WHEN rc.tipo_registro = 1 AND p.qtd_dados - rc.consumo IS NOT NULL\r\n" + 
			"               THEN p.qtd_dados - rc.consumo\r\n" + 
			"           WHEN rc.tipo_registro = 1 AND p.qtd_dados - rc.consumo < 0\r\n" + 
			"               THEN 0\r\n" + 
			"           ELSE\r\n" + 
			"               p.qtd_dados\r\n" + 
			"           END AS saldo_dados,\r\n" + 
			"       CASE\r\n" + 
			"           WHEN rc.tipo_registro = 2 AND p.qtd_sms - rc.consumo IS NOT NULL\r\n" + 
			"               THEN p.qtd_sms - rc.consumo\r\n" + 
			"           WHEN rc.tipo_registro = 2 AND p.qtd_sms - rc.consumo < 0\r\n" + 
			"               THEN 0\r\n" + 
			"           ELSE p.qtd_sms\r\n" + 
			"           END AS saldo_sms\r\n" + 
			"FROM produto p\r\n" + 
			"         LEFT JOIN registro_cdr rc\r\n" + 
			"                   ON p.numero = rc.origem\r\n" + 
			"WHERE cliente_id = ", nativeQuery = true)
	SaldoDTO consultar(Long clienteId);	
}
