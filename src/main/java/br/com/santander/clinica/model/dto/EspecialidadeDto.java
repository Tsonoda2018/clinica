package br.com.santander.clinica.model.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Especialidade;

public class EspecialidadeDto extends RepresentationModel<EspecialidadeDto> {

	private String nome;

	public EspecialidadeDto(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static EspecialidadeDto converte(Especialidade especialidade) {
		return new EspecialidadeDto(especialidade.getNome());
	}

}
