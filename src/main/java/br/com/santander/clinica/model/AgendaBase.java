package br.com.santander.clinica.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public class AgendaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private LocalDate dataLivre;

	public AgendaBase(Integer id, LocalDate dataLivre) {
		super();
		this.id = id;
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
}
