package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Agenda;

public class AgendaDto extends RepresentationModel<AgendaDto> {

	private Integer id;
	private MedicoDto medicoDto;
	private LocalDate data;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public AgendaDto(Integer id, MedicoDto medicoDto, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim) {
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public MedicoDto getMedicoDto() {
		return medicoDto;
	}

	public static AgendaDto converte(Agenda agenda) {
		return new AgendaDto(agenda.getAgendaId().getId(), MedicoDto.converte(agenda.getMedico()), agenda.getAgendaId().getDataLivre(),
				agenda.getHorarioInicio(), agenda.getHorarioFim());
	}

}
