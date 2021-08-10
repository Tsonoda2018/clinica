package br.com.santander.clinica.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.AgendaBase;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, AgendaBase>, JpaSpecificationExecutor<Agenda> {

	List<Agenda> findAllByAgendaIdMedicoId(Integer id);

	Agenda findByAgendaIdDataLivreAndHorarioInicioLessThanEqualAndHorarioFimGreaterThan(LocalDate dataAgendamento,
			LocalTime horaInicioAgendamento, LocalTime horaFimAgendamento);

	List<Agenda> findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndPacienteIdIsNotNull(Integer medicoId, LocalDate data);

	List<Agenda> findAllByAgendaIdId(Integer id);

	List<Agenda> findAllByAgendaIdMedicoIdAndAgendaIdDataLivreGreaterThanEqualAndPacienteIdIsNull(Integer id, LocalDate now);

	List<Agenda> findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFim(Integer id, LocalDate data,
			LocalTime horarioInicio, LocalTime horarioFim);

	Agenda findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFimAndPacienteIdIsNull(Integer id,
			LocalDate dataLivre, LocalTime horarioInicio, LocalTime horarioFim);

	boolean existsByAgendaIdDataLivreAndHorarioInicioAndHorarioFim(LocalDate dataAgendamento,
			LocalTime horaInicioAgendamento, LocalTime horaFimAgendamento);

}
