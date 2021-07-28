package br.com.santander.clinica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dataConsulta;
	private LocalDateTime horarioIncio;
	private LocalDateTime horarioFim;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Medico medico;
	
	protected Consulta() {
	}

	public Consulta(LocalDate dataConsulta, Paciente paciente, Medico medico) {
		this.dataConsulta = dataConsulta;
		this.paciente = paciente;
		this.medico = medico;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public LocalDateTime getHorarioIncio() {
		return horarioIncio;
	}

	public LocalDateTime getHorarioFim() {
		return horarioFim;
	}
	
	

}
