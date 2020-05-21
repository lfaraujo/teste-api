package br.com.vivoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vivoapi.model.RegistroCdr;

@Repository
public interface RegistroCdrRepository extends JpaRepository<RegistroCdr, Long> {

}
