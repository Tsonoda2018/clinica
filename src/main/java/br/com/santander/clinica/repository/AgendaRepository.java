package br.com.santander.clinica.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.clinica.model.Agenda;


@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

	List<Agenda> findAllByMedicoId(Integer id);

	Agenda findByDataLivreAndHorarioInicioLessThanEqualAndHorarioFimGreaterThan(LocalDate dataAgendamento,
			LocalTime horaInicioAgendamento, LocalTime horaFimAgendamento);

	List<Agenda> findAllByMedicoIdAndDataLivre(Integer medicoId, LocalDate data);

}
