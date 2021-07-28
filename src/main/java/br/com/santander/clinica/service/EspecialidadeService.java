package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Especialidade;

public interface EspecialidadeService {
	Especialidade salvar(Especialidade especialidade);

	List<Especialidade> buscarTodos();

	Especialidade buscarPorId(Integer id);

	void excluir(Integer id);
}
