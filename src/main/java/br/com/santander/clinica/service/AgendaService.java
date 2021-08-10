package br.com.santander.clinica.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.AgendaBase;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.model.dto.FiltroAgendaDto;

public interface AgendaService {
	Agenda salvar(Agenda agenda);

	List<Agenda> buscarTodos(FiltroAgendaDto filtro);

	List<Agenda> buscarPorId(Integer id);

	Agenda buscarPorId(AgendaBase id);

	void excluir(AgendaBase id);

	Agenda agendar(Medico medico, Paciente paciente, LocalDate dataAgendamento, LocalTime horaInicioAgendamento,
			LocalTime horaFimAgendamento);
}
