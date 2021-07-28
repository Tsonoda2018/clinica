package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Agenda;

public class AgendaDto extends RepresentationModel<MedicoDto> {

	private MedicoDto medicoDto;
	private LocalDate data;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public AgendaDto(MedicoDto medicoDto, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim) {
		this.medicoDto = medicoDto;
		this.data = data;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	public MedicoDto getMedico() {
		return medicoDto;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public static AgendaDto converte(Agenda agenda) {
		return new AgendaDto(MedicoDto.converte(agenda.getMedico()), agenda.getDataLivre(), agenda.getHorarioInicio(),
				agenda.getHorarioFim());
	}

}
