package br.com.santander.clinica.model.dto;

import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Agenda;

public class AgendaPacienteDto extends RepresentationModel<AgendaPacienteDto> {
	private PacienteDto paciente;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;

	public AgendaPacienteDto(PacienteDto paciente, LocalTime horarioInicio, LocalTime horarioFim) {
		super();
		this.paciente = paciente;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}

	public PacienteDto getPaciente() {
		return paciente;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public static AgendaPacienteDto converte(Agenda agenda) {
		return new AgendaPacienteDto(PacienteDto.converte(agenda.getPaciente()), agenda.getHorarioInicio(),
				agenda.getHorarioFim());
	}

}
