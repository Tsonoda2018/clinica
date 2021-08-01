package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Agenda;

public class PacienteAgendamentoDto extends RepresentationModel<PacienteAgendamentoDto> {

	private String nomePaciente;
	private String nomeMedico;
	private LocalDate dataConsulta;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public PacienteAgendamentoDto(String nomePaciente, String nomeMedico, LocalDate dataConsulta,
			LocalTime horarioInicio, LocalTime horarioFim) {
		super();
		this.nomePaciente = nomePaciente;
		this.nomeMedico = nomeMedico;
		this.dataConsulta = dataConsulta;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public static PacienteAgendamentoDto converte(Agenda agendamento) {
		return new PacienteAgendamentoDto(agendamento.getPaciente().getNome(), agendamento.getAgendaId().getMedico().getNome(), agendamento.getAgendaId().getDataLivre(), agendamento.getHorarioInicio(), agendamento.getHorarioFim());
	}

}
