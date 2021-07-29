package br.com.santander.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

	List<Consulta> findAllByPacienteId(Integer id);

	List<Consulta> findAllByMedicoId(Integer id);

}
