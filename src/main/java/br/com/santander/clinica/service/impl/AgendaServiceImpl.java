package br.com.santander.clinica.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.AgendaBase;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendaPacienteDto;
import br.com.santander.clinica.repository.AgendaRepository;
import br.com.santander.clinica.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService {

	private final AgendaRepository agendaRepository;

	public AgendaServiceImpl(AgendaRepository agendaRepository) {
		super();
		this.agendaRepository = agendaRepository;
	}

	@Override
	public Agenda salvar(Agenda agenda) {
		if (!agendaRepository.existsById(agenda.getAgendaId()) || this.validaDisponibilidade(agenda)) {
			return this.agendaRepository.save(agenda);
		}

		throw new EntityExistsException("Agenda do dia " + agenda.getAgendaId().getDataLivre() + " já liberada.");
	}

	@Override
	public List<Agenda> buscarTodos() {
		return this.agendaRepository.findAll();
	}

	@Override
	public Agenda buscarPorId(AgendaBase id) {
		return this.agendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe agenda com o id " + id));
	}

	@Override
	public void excluir(AgendaBase id) {
		this.agendaRepository.deleteById(id);

	}

	public List<Agenda> buscarAgendaPorMedico(Medico medico) {
		return this.agendaRepository.findAllByAgendaIdMedicoIdAndAgendaIdDataLivreGreaterThanEqualAndPacienteIdIsNull(
				medico.getId(), LocalDate.now());

	}

	public boolean validaDisponibilidade(Agenda agenda) {
		Agenda agendaLivre = agendaRepository
				.findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFimAndPacienteIdIsNull(
						agenda.getAgendaId().getMedico().getId(), agenda.getAgendaId().getDataLivre(), agenda.getHorarioInicio(),
						agenda.getHorarioFim());
		if (agendaLivre == null) {
			throw new EntityExistsException("Agenda do dia " + agenda.getAgendaId().getDataLivre() + " Inicio: "
					+ agenda.getHorarioInicio() + " - Fim: " + agenda.getHorarioFim() + " já reservada");
//			return false;
		}

		return true;

	}

	public List<AgendaDto> liberarAgenda(Medico medico, LocalDate data) {
		LocalTime horarioInicio = LocalTime.of(8, 0);
		List<AgendaDto> dtos = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			LocalTime horarioFim = horarioInicio.plusHours(1);
			if (!horarioInicio.equals(LocalTime.of(12, 0)))
				dtos.add(AgendaDto
						.converte(this.salvar(new Agenda(new AgendaBase(i, medico, data), horarioInicio, horarioFim))));
			horarioInicio = horarioFim;
		}

		return dtos;

	}

	public List<AgendaPacienteDto> buscarAgendaPorData(Medico medico, LocalDate data) {
		List<Agenda> dto = agendaRepository.findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndPacienteIdIsNotNull(medico.getId(),
				data);
		List<AgendaPacienteDto> agenda = dto.stream().map(a -> AgendaPacienteDto.converte(a))
				.collect(Collectors.toList());
		return agenda;

	}

	@Override
	public List<Agenda> buscarPorId(Integer id) {
		return agendaRepository.findAllByAgendaIdId(id);
	}

	@Override
	public Agenda agendar(Medico medico, Paciente paciente, LocalDate dataAgendamento, LocalTime horaInicioAgendamento,
			LocalTime horaFimAgendamento) {
		if (!agendaRepository.existsByAgendaIdDataLivreAndHorarioInicioAndHorarioFim(dataAgendamento, horaInicioAgendamento, horaFimAgendamento)) {
			throw new EntityNotFoundException("Data/horário não disponível.");
		}
		Agenda agenda = agendaRepository.findAllByAgendaIdMedicoIdAndAgendaIdDataLivreAndHorarioInicioAndHorarioFim(
				medico.getId(), dataAgendamento, horaInicioAgendamento, horaFimAgendamento).get(0);
		agenda.setPaciente(paciente);
		return this.salvar(agenda);

	}

}
