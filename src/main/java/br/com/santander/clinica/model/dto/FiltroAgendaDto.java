package br.com.santander.clinica.model.dto;

public class FiltroAgendaDto {
	private String nomeMedico;
	private String nomePaciente;
	private String especialidade;

	public FiltroAgendaDto(String nomeMedico, String nomePaciente, String especialidade) {
		this.nomeMedico = nomeMedico;
		this.nomePaciente = nomePaciente;
		this.especialidade = especialidade;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public String getEspecialidade() {
		return especialidade;
	}

}
