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

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

}
