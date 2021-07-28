package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.repository.PacienteRepository;
import br.com.santander.clinica.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	private final PacienteRepository pacienteRepository;

	public PacienteServiceImpl(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@Override
	public Paciente salvar(Paciente paciente) {
		return this.pacienteRepository.save(paciente);
	}

	@Override
	public List<Paciente> buscarTodos() {
		return this.pacienteRepository.findAll();
	}

	@Override
	public Paciente buscarPorId(Integer id) {
		return this.pacienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe paciente com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.pacienteRepository.deleteById(id);

	}

}
