package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.santander.clinica.model.Endereco;
import br.com.santander.clinica.model.Medico;

public class MedicoInputDto {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	private Set<Endereco> enderecos = new HashSet<>();

	private String crm;
	private Integer idEspecialidade;

	public MedicoInputDto(String nome, String cpf, LocalDate dataNascimento, Set<Endereco> enderecos, String crm,
			Integer idEspecialidade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
		this.crm = crm;
		this.idEspecialidade = idEspecialidade;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public String getCrm() {
		return crm;
	}

	public Integer getIdEspecialidade() {
		return idEspecialidade;
	}

	public Medico converte() {
		Medico medico = new Medico(nome, cpf, dataNascimento, crm, null);
		enderecos.forEach(e -> medico.adicionaEndereco(e));
		return medico;
	}

	
}
