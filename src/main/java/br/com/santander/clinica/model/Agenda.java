package br.com.santander.clinica.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JsonIgnore
	private Medico medico;
	@ManyToOne
	@JsonIgnore
	private Paciente paciente;

	private LocalDate dataLivre;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public Agenda(Medico medico, LocalDate dataLivre, LocalTime horarioInicio, LocalTime horarioFim) {
		super();
		this.medico = medico;
		this.dataLivre = dataLivre;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	protected Agenda() {
	}

	public Integer getId() {
		return id;
	}

	public Medico getMedico() {
		return medico;
	}

	public LocalDate getDataLivre() {
		return dataLivre;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void setDataLivre(LocalDate dataLivre) {
		this.dataLivre = dataLivre;
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

}
