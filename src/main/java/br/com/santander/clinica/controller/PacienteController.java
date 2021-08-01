package br.com.santander.clinica.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendamentoDto;
import br.com.santander.clinica.model.dto.MedicoDto;
import br.com.santander.clinica.model.dto.PacienteAgendamentoDto;
import br.com.santander.clinica.model.dto.PacienteDto;
import br.com.santander.clinica.model.dto.PacienteInputDto;
import br.com.santander.clinica.service.EspecialidadeService;
import br.com.santander.clinica.service.MedicoService;
import br.com.santander.clinica.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	private final PacienteService pacienteService;
	private final MedicoService medicoService;
	private final EspecialidadeService especialidadeService;

	public PacienteController(PacienteService pacienteService, MedicoService medicoService,
			EspecialidadeService especialidadeService) {
		super();
		this.pacienteService = pacienteService;
		this.medicoService = medicoService;
		this.especialidadeService = especialidadeService;
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid PacienteInputDto pacienteInputDto,
			UriComponentsBuilder uriBuilder) {
		Paciente paciente = pacienteInputDto.converte();
		Paciente pacienteSalvo = pacienteService.salvar(paciente);
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
		Link self = linkTo(PacienteController.class).slash(pacienteSalvo.getId()).withSelfRel();
		Link pacientes = linkTo(PacienteController.class).withRel("pacientes");
		PacienteDto pacienteDto = PacienteDto.converte(pacienteSalvo);
		return ResponseEntity.created(uri).body(pacienteDto.add(self).add(pacientes));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Integer id) {
		PacienteDto pacientesDto = PacienteDto.converte(pacienteService.buscarPorId(id));
		Link self = linkTo(PacienteController.class).slash(id).withSelfRel();
		Link pacientes = linkTo(PacienteController.class).withRel("pacientes");
		Link agendar = linkTo(PacienteController.class).slash("agendamento").withRel("agendar").withType("put");
		return ResponseEntity.ok(pacientesDto.add(self).add(pacientes).add(agendar));
	}

	@GetMapping
	public ResponseEntity<List<PacienteDto>> buscarTodos() {
		List<PacienteDto> dtos = pacienteService.buscarTodos().stream().map(m -> {
			PacienteDto dto = PacienteDto.converte(m);
			Link self = linkTo(PacienteController.class).slash(m.getId()).withSelfRel();
			Link pacientes = linkTo(PacienteController.class).withRel("pacientes");
			dto.add(self).add(pacientes);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("medicos/especialidades/{id}")
	public ResponseEntity<?> buscarPorEspecialidade(@PathVariable Integer id) {
		List<MedicoDto> dtos = medicoService.buscarPorEspecialidade(especialidadeService.buscarPorId(id)).stream()
				.map(m -> {
					MedicoDto dto = MedicoDto.converte(m);
					Link self = linkTo(MedicoController.class).slash(m.getId()).withSelfRel();
					Link medicos = linkTo(MedicoController.class).withRel("medicos");
					Link agenda = linkTo(PacienteController.class).slash("medicos/agenda/" + m.getId())
							.withRel("agenda");
					dto.add(self).add(medicos).add(agenda);
					return dto;
				}).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("medicos/agenda/{id}")
	public ResponseEntity<?> buscarAgendaMedico(@PathVariable Integer id) {
		List<AgendaDto> dtos = medicoService.consultarAgenda(medicoService.buscarPorId(id));
		return ResponseEntity.ok(dtos);
	}

	@PutMapping("/agendamento")
	public ResponseEntity<?> agendar(@RequestBody @Valid AgendamentoDto agendamentoDto,
			UriComponentsBuilder uriBuilder) {
		PacienteAgendamentoDto agendaConsulta = pacienteService.agendaConsulta(agendamentoDto);
//		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
		Link self = linkTo(PacienteController.class).slash(agendamentoDto.getIdPaciente()).withSelfRel();
		Link pacientes = linkTo(PacienteController.class).withRel("pacientes");
//		PacienteDto pacienteDto = PacienteDto.converte(pacienteSalvo);
		return ResponseEntity.ok(agendaConsulta.add(self).add(pacientes));
	}

}
