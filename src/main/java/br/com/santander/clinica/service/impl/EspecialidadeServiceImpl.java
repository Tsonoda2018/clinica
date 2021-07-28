package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.repository.EspecialidadeRepository;
import br.com.santander.clinica.service.EspecialidadeService;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {
	
	private final EspecialidadeRepository especialidadeRepository;
	
	public EspecialidadeServiceImpl(EspecialidadeRepository especialidadeRepository) {
		this.especialidadeRepository = especialidadeRepository;
	}

	@Override
	public Especialidade salvar(Especialidade especialidade) {
		return this.especialidadeRepository.save(especialidade);
	}

	@Override
	public List<Especialidade> buscarTodos() {
		return this.especialidadeRepository.findAll();
	}

	@Override
	public Especialidade buscarPorId(Integer id) {
		return this.especialidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe especialidade com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.especialidadeRepository.deleteById(id);
		
	}

}
