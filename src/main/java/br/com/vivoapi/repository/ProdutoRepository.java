package br.com.vivoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByNumero(String numero);
}
