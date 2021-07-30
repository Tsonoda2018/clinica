package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.AgendaBase;

public interface AgendaService {
	Agenda salvar(Agenda agenda);

	List<Agenda> buscarTodos();

	List<Agenda> buscarPorId(Integer id);

	Agenda buscarPorId(AgendaBase id);

	void excluir(AgendaBase id);
}
