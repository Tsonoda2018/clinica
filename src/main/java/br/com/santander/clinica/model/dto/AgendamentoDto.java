package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDto {
	private Integer idMedico;
	private Integer idPaciente;
	private LocalDate dataAgendamento;
	private LocalTime horaInicioAgendamento;
	private LocalTime horaFimAgendamento;

	public AgendamentoDto(Integer idMedico, Integer idPaciente, LocalDate dataAgendamento,
			LocalTime horaInicioAgendamento, LocalTime horaFimAgendamento) {
		super();
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.dataAgendamento = dataAgendamento;
		this.horaInicioAgendamento = horaInicioAgendamento;
		this.horaFimAgendamento = horaFimAgendamento;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public LocalTime getHoraInicioAgendamento() {
		return horaInicioAgendamento;
	}

	public LocalTime getHoraFimAgendamento() {
		return horaFimAgendamento;
	}

}
