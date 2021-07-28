package br.com.santander.clinica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Medico extends Pessoa {

	private String crm;
	@ManyToOne
	private Especialidade especialidade;
	@OneToMany(mappedBy = "medico")
	private List<Consulta> consultas = new ArrayList<>();
	@OneToMany(mappedBy = "medico")
	private List<Agenda> agenda = new ArrayList<>();

	public Medico(String nome, String cpf, LocalDate dataNascimento, String crm,
			Especialidade especialidade) {
		super(nome, cpf, dataNascimento);
		this.crm = crm;
		this.especialidade = especialidade;
	}

	protected Medico() {
	}

	public String getCrm() {
		return crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
		 
	}
	
	public List<Consulta> getConsultas() {
		return Collections.unmodifiableList(consultas);
	}

	public List<Agenda> getAgenda() {
		return Collections.unmodifiableList(agenda);
	}
	
	

}
