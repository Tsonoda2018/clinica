package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendaInputDto;

public interface MedicoService {
	Medico salvar(Medico medico);

	List<Medico> buscarTodos();

	List<Medico> buscarPorEspecialidade(Especialidade especialidade);

	Medico buscarPorId(Integer id);

	void excluir(Integer id);
	
	AgendaDto liberarAgenda(AgendaInputDto agendaInputDto);
	
	List<AgendaDto> consultarAgenda(Medico medico);
}
