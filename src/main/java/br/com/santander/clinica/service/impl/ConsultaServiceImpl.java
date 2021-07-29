package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Consulta;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.repository.ConsultaRepository;
import br.com.santander.clinica.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	private final ConsultaRepository consultaRepository;

	public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}

	@Override
	public Consulta salvar(Consulta consulta) {
		return this.consultaRepository.save(consulta);
	}

	@Override
	public List<Consulta> buscarTodosPorPaciente(Paciente paciente) {
		return this.consultaRepository.findAllByPacienteId(paciente.getId());
	}

	@Override
	public List<Consulta> buscarTodosPorMedico(Medico medico) {
		return this.consultaRepository.findAllByMedicoId(medico.getId());
	}

	@Override
	public Consulta buscarPorId(Integer id) {
		return this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe consulta com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		this.consultaRepository.deleteById(id);

	}

}
