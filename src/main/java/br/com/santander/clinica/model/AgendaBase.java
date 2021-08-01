package br.com.santander.clinica.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Embeddable
public class AgendaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@ManyToOne
	@JsonIgnore
	private Medico medico;
	private LocalDate dataLivre;

	public AgendaBase(Integer id, Medico medico, LocalDate dataLivre) {
		super();
		this.id = id;
		this.medico = medico;
		this.dataLivre = dataLivre;
	}

	protected AgendaBase() {
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDataLivre() {
		return dataLivre;
	}

	public void setDataLivre(LocalDate dataLivre) {
		this.dataLivre = dataLivre;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medico getMedico() {
		return medico;
	}
}
