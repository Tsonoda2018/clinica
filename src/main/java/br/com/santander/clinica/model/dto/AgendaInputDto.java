package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.Medico;

public class AgendaInputDto {

	private Integer idMedico;
	private LocalDate data;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public AgendaInputDto(Integer idMedico, LocalDate data, LocalTime  horarioInicio, LocalTime  horarioFim) {
		super();
		this.idMedico = idMedico;
		this.data = data;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime  getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime  getHorarioFim() {
		return horarioFim;
	}

	public static Agenda converte(Medico medico, AgendaInputDto agendaDto) {
		return new Agenda(medico, agendaDto.getData(), agendaDto.getHorarioInicio(), agendaDto.getHorarioFim());
	}

}
