package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.repository.MedicoRepository;
import br.com.santander.clinica.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {
	
	private final MedicoRepository medicoRepository;
	
	public MedicoServiceImpl(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
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
		return this.medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe médico com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.medicoRepository.deleteById(id);
		
	}

	@Override
	public List<Medico> buscarPorEspecialidade(Especialidade especialidade) {
		return this.medicoRepository.findAllByEspecialidadeId(especialidade.getid());
	}

}
