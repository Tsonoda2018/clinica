package br.com.santander.clinica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Paciente extends Pessoa {
	
	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas = new ArrayList<>();
	
	protected Paciente() {
	}
	
	public Paciente(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
	}

}
