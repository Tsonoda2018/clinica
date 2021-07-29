package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.repository.AgendaRepository;
import br.com.santander.clinica.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService {

	private final AgendaRepository agendaRepository;

	public AgendaServiceImpl(AgendaRepository agendaRepository) {
		this.agendaRepository = agendaRepository;
	}

	@Override
	public Agenda salvar(Agenda agenda) {
		return this.agendaRepository.save(agenda);
	}

	@Override
	public List<Agenda> buscarTodos() {
		return this.agendaRepository.findAll();
	}

	@Override
	public Agenda buscarPorId(Integer id) {
		return this.agendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe agenda com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.agendaRepository.deleteById(id);

	}

	public List<Agenda> buscarAgendaPorMedico(Medico medico) {
		return this.agendaRepository.findAllByMedicoId(medico.getId());

	}

}
