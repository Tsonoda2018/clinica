package br.com.santander.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
}
