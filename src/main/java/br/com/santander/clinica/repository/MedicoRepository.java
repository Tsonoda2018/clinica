package br.com.santander.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer>, JpaSpecificationExecutor<Medico> {

	List<Medico> findAllByEspecialidadeId(Integer idEspecialidade);
}
