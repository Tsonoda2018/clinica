package br.com.santander.clinica.service;

import java.time.LocalDate;
import java.util.List;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendaInputDto;
import br.com.santander.clinica.model.dto.AgendaPacienteDto;
import br.com.santander.clinica.model.dto.MedicoFiltroDto;

public interface MedicoService {
	Medico salvar(Medico medico);

	List<Medico> buscarPorEspecialidade(Especialidade especialidade);

	Medico buscarPorId(Integer id);

	void excluir(Integer id);

	List<AgendaDto> liberarAgenda(AgendaInputDto agendaInputDto);

	List<AgendaDto> consultarAgenda(Medico medico);

	List<AgendaPacienteDto> consutarPacientePorData(Medico medico, LocalDate data);

	List<Medico> buscarTodos(MedicoFiltroDto filtro);

}
