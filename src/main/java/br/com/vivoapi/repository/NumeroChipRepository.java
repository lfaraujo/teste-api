package br.com.vivoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.NumeroChip;

@Repository
public interface NumeroChipRepository extends JpaRepository<NumeroChip, Long> {

	Optional<NumeroChip> findByNumero(String numero);

}
