package br.com.santander.clinica.model.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Paciente;

public class PacienteDto extends RepresentationModel<PacienteDto> {
	private String nome;
	private String cpf;
	

	public PacienteDto(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public String getCpf() {
		return cpf;
	}


	public static PacienteDto converte(Paciente paciente) {
		return new PacienteDto(paciente.getNome(), paciente.getCpf());
	}

}
