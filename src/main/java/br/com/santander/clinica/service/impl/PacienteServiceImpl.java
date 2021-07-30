package br.com.santander.clinica.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.model.dto.AgendamentoDto;
import br.com.santander.clinica.repository.PacienteRepository;
import br.com.santander.clinica.service.MedicoService;
import br.com.santander.clinica.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	private final PacienteRepository pacienteRepository;
	private final MedicoService medicoService;


	public PacienteServiceImpl(PacienteRepository pacienteRepository, MedicoService medicoService) {
		super();
		this.pacienteRepository = pacienteRepository;
		this.medicoService = medicoService;
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

	@Override
	public void agendaConsulta(AgendamentoDto agendamentoDto) {
//		Medico medico = medicoService.buscarPorId(agendamentoDto.getIdMedico());
//		Paciente paciente = this.buscarPorId(agendamentoDto.getIdPaciente());
//		if (medicoService.validaDisponibilidade(agendamentoDto.getDataAgendamento(),
//				agendamentoDto.getHoraInicioAgendamento(), agendamentoDto.getHoraFimAgendamento())) {
//			System.out.println("tem agenda livre");
//			
//			Consulta consulta = new Consulta(agendamentoDto.getDataAgendamento(), agendamentoDto.getHoraInicioAgendamento(), agendamentoDto.getHoraFimAgendamento(), paciente, medico);
//			consultaService.salvar(consulta);
//		} else {
//			System.out.println("Não tem agenda");
//		}

	}

}
