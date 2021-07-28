package br.com.santander.clinica.model.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.santander.clinica.model.Medico;

public class MedicoDto extends RepresentationModel<MedicoDto> {
	private String nome;
	private String crm;
	private String nomeEspecialidade;

	public MedicoDto(String nome, String crm, String nomeEspecialidade) {
		this.nome = nome;
		this.crm = crm;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public MedicoDto(String nome, String crm) {
		this.nome = nome;
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public String getCrm() {
		return crm;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public static MedicoDto converte(Medico medico) {
		return new MedicoDto(medico.getNome(), medico.getCrm(), medico.getEspecialidade().getNome());
	}

}
