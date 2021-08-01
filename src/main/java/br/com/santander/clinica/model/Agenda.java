package br.com.santander.clinica.model;

import java.time.LocalTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Agenda {

	@EmbeddedId
	private AgendaBase agendaId;

	@ManyToOne
	@JsonIgnore
	private Paciente paciente;

	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public Agenda(AgendaBase agendaId, LocalTime horarioInicio, LocalTime horarioFim) {
		super();
		this.agendaId = agendaId;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	protected Agenda() {
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public void setHorarioFim(LocalTime horarioFim) {
		this.horarioFim = horarioFim;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public AgendaBase getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(AgendaBase agendaId) {
		this.agendaId = agendaId;
	}

}
