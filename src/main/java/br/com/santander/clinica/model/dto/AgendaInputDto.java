package br.com.santander.clinica.model.dto;

import java.time.LocalDate;

public class AgendaInputDto {

	private Integer idMedico;
	private LocalDate data;

	public AgendaInputDto(Integer idMedico, LocalDate data) {
		super();
		this.idMedico = idMedico;
		this.data = data;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public LocalDate getData() {
		return data;
	}

}
