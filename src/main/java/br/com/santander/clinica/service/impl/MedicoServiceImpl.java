package br.com.santander.clinica.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendaInputDto;
import br.com.santander.clinica.repository.MedicoRepository;
import br.com.santander.clinica.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

	private final MedicoRepository medicoRepository;
	private final AgendaServiceImpl agendaService;

	public MedicoServiceImpl(MedicoRepository medicoRepository, AgendaServiceImpl agendaService) {
		super();
		this.medicoRepository = medicoRepository;
		this.agendaService = agendaService;
	}

	@Override
	public Medico salvar(Medico medico) {
		return this.medicoRepository.save(medico);
	}

	@Override
	public List<Medico> buscarTodos() {
		return this.medicoRepository.findAll();
	}

	@Override
	public Medico buscarPorId(Integer id) {
		return this.medicoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe médico com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.medicoRepository.deleteById(id);

	}

	@Override
	public List<Medico> buscarPorEspecialidade(Especialidade especialidade) {
		return this.medicoRepository.findAllByEspecialidadeId(especialidade.getid());
	}

	@Override
	public AgendaDto liberarAgenda(AgendaInputDto agendaInputDto) {
		Medico medicoSalvo = this.buscarPorId(agendaInputDto.getIdMedico());
		return AgendaDto.converte(agendaService.salvar(AgendaInputDto.converte(medicoSalvo, agendaInputDto)));

	}

	@Override
	public List<AgendaDto> consultarAgenda(Medico medico) {
		return agendaService.buscarAgendaPorMedico(medico).stream().map(a -> AgendaDto.converte(a))
				.collect(Collectors.toList());
	}
}
