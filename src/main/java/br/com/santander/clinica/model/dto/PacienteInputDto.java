package br.com.santander.clinica.model.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.santander.clinica.model.Endereco;
import br.com.santander.clinica.model.Paciente;

public class PacienteInputDto {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	private Set<Endereco> enderecos = new HashSet<>();

	public PacienteInputDto(String nome, String cpf, LocalDate dataNascimento, Set<Endereco> enderecos, String crm,
			Integer idEspecialidade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
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


	public Paciente converte() {
		Paciente paciente = new Paciente(nome, cpf, dataNascimento);
		enderecos.forEach(e -> paciente.adicionaEndereco(e));
		return paciente;
	}

	
}
