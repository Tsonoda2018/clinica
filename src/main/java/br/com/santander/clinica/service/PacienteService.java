package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Paciente;

public interface PacienteService {
	Paciente salvar(Paciente paciente);

	List<Paciente> buscarTodos();

	Paciente buscarPorId(Integer id);

	void excluir(Integer id);
}
