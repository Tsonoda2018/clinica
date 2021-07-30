package br.com.santander.clinica.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.santander.clinica.model.Agenda;
import br.com.santander.clinica.model.Medico;
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

	public boolean validaDisponibilidade(LocalDate dataAgendamento, LocalTime horaInicioAgendamento,
			LocalTime horaFimAgendamento) {
		Agenda agendaLivre = agendaRepository.findByDataLivreAndHorarioInicioLessThanEqualAndHorarioFimGreaterThan(
				dataAgendamento, horaInicioAgendamento, horaFimAgendamento);
		if (agendaLivre == null) {
			return false;
		}

		return true;

	}

	public List<AgendaDto> liberarAgenda(Medico medico, LocalDate data) {
		LocalTime horarioInicio = LocalTime.of(8, 0);
		List<AgendaDto> dtos = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			LocalTime horarioFim = horarioInicio.plusHours(1);
			if (!horarioInicio.equals(LocalTime.of(12, 0)))
				dtos.add(AgendaDto.converte(this.salvar(new Agenda(medico, data, horarioInicio, horarioFim))));
			horarioInicio = horarioFim;
		}

		return dtos;

	}

	public List<AgendaPacienteDto> buscarAgendaPorData(Medico medico, LocalDate data) {
		List<Agenda> dto = agendaRepository.findAllByMedicoIdAndDataLivre(medico.getId(), data);
		List<AgendaPacienteDto> collect = dto.stream().map(a -> AgendaPacienteDto.converte(a))
				.collect(Collectors.toList());
		return collect;

	}

}
