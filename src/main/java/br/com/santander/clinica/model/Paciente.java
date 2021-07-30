package br.com.santander.clinica.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Paciente extends Pessoa {
	
	protected Paciente() {
	}
	
	public Paciente(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
	}

}
