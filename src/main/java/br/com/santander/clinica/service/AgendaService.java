package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Agenda;

public interface AgendaService {
	Agenda salvar(Agenda agenda);

	List<Agenda> buscarTodos();

	Agenda buscarPorId(Integer id);

	void excluir(Integer id);
}
