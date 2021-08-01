package br.com.santander.clinica.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.AgendaBase;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, AgendaBase> {

	List<Agenda> findAllByMedicoId(Integer id);

	Agenda findByAgendaIdDataLivreAndHorarioInicioLessThanEqualAndHorarioFimGreaterThan(LocalDate dataAgendamento,
			LocalTime horaInicioAgendamento, LocalTime horaFimAgendamento);

	List<Agenda> findAllByMedicoIdAndAgendaIdDataLivreAndPacienteIdIsNotNull(Integer medicoId, LocalDate data);

	List<Agenda> findAllByAgendaIdId(Integer id);

	List<Agenda> findAllByMedicoIdAndAgendaIdDataLivreGreaterThanEqualAndPacienteIdIsNull(Integer id, LocalDate now);

	List<Agenda> findAllByMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFim(Integer id, LocalDate data,
			LocalTime horarioInicio, LocalTime horarioFim);

	Agenda findAllByMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFimAndPacienteIdIsNull(Integer id,
			LocalDate dataLivre, LocalTime horarioInicio, LocalTime horarioFim);

}
