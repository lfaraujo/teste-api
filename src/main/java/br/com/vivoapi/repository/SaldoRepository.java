package br.com.vivoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {

	Saldo findByProduto(Long idProduto);

}
