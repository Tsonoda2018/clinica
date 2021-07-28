package br.com.santander.clinica.model;

import java.time.LocalDate;

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
	
	

}
