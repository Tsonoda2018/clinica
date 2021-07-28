package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;

public interface MedicoService {
	Medico salvar(Medico medico);

	List<Medico> buscarTodos();

	List<Medico> buscarPorEspecialidade(Especialidade especialidade);

	Medico buscarPorId(Integer id);

	void excluir(Integer id);
}
