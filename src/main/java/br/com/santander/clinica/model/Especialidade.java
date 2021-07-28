package br.com.santander.clinica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especialidade")
public class Especialidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	protected Especialidade() {
	}

	public Especialidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Integer getid() {
		return id;
	}
	
	

}
