package br.com.santander.clinica.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.dto.AgendaDto;
import br.com.santander.clinica.model.dto.AgendaInputDto;
import br.com.santander.clinica.model.dto.AgendaPacienteDto;
import br.com.santander.clinica.model.dto.MedicoDto;
import br.com.santander.clinica.model.dto.MedicoInputDto;
import br.com.santander.clinica.service.EspecialidadeService;
import br.com.santander.clinica.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	private final MedicoService medicoService;
	private final EspecialidadeService especialidadeService;

	public MedicoController(MedicoService medicoService, EspecialidadeService especialidadeService) {
		super();
		this.medicoService = medicoService;
		this.especialidadeService = especialidadeService;
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid MedicoInputDto medicoInputDto,
			UriComponentsBuilder uriBuilder) {
		Medico medico = medicoInputDto.converte();
		medico.setEspecialidade(especialidadeService.buscarPorId(medicoInputDto.getIdEspecialidade()));
		Medico medicoSalvo = medicoService.salvar(medico);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoSalvo.getId()).toUri();
		Link self = linkTo(MedicoController.class).slash(medicoSalvo.getId()).withSelfRel();
		Link medicos = linkTo(MedicoController.class).withRel("medicos");
		Link especialidade = linkTo(EspecialidadeController.class).slash(medicoSalvo.getEspecialidade().getid())
				.withRel("especialidades");
		MedicoDto medicoDto = MedicoDto.converte(medicoSalvo);
		return ResponseEntity.created(uri).body(medicoDto.add(self).add(medicos).add(especialidade));
	}

	@PostMapping("/agenda/{idMedico}")
	public ResponseEntity<?> liberarAgenda(@RequestBody @Valid AgendaInputDto agendaInputDto,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/medicos/agenda/{id}").buildAndExpand(agendaInputDto.getIdMedico()).toUri();

		List<AgendaDto> agenda = medicoService.liberarAgenda(agendaInputDto);
		agenda.forEach(a -> {
			Link self = linkTo(MedicoController.class).slash(agendaInputDto.getIdMedico()).withSelfRel();
			a.add(self);
		});
		return ResponseEntity.created(uri).body(agenda);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MedicoDto> buscarPorId(@PathVariable Integer id) {
		MedicoDto medicosDto = MedicoDto.converte(medicoService.buscarPorId(id));
		Link self = linkTo(MedicoController.class).slash(id).withSelfRel();
		Link medicos = linkTo(MedicoController.class).withRel("medicos");
		Link pacientesPorData = linkTo(MedicoController.class).slash("/pacientes/" + id +"?data=" + LocalDate.now()).withRel("pacientesPorData");
		return ResponseEntity.ok(medicosDto.add(self).add(medicos).add(pacientesPorData));
	}

	@GetMapping
	public ResponseEntity<List<MedicoDto>> buscarTodos() {
		List<MedicoDto> dtos = medicoService.buscarTodos().stream().map(m -> {
			MedicoDto dto = MedicoDto.converte(m);
			Link self = linkTo(MedicoController.class).slash(m.getId()).withSelfRel();
			Link medicos = linkTo(MedicoController.class).withRel("medicos");
			dto.add(self).add(medicos);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/especialidades/{id}")
	public ResponseEntity<?> buscarPorEspecialidade(@PathVariable Integer id) {
		List<MedicoDto> dtos = medicoService.buscarPorEspecialidade(especialidadeService.buscarPorId(id)).stream()
				.map(m -> {
					MedicoDto dto = MedicoDto.converte(m);
					Link self = linkTo(MedicoController.class).slash(m.getId()).withSelfRel();
					Link medicos = linkTo(MedicoController.class).withRel("medicos");
					dto.add(self).add(medicos);
					return dto;
				}).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/pacientes/{medicoId}")
	public ResponseEntity<?> buscarPacientesPorData(@PathVariable Integer medicoId, String data) {
		LocalDate dataParse = LocalDate.parse(data);
		List<AgendaPacienteDto> dtos = medicoService.consutarPacientePorData(medicoService.buscarPorId(medicoId), dataParse);
		dtos.forEach(a -> {
			Link self = linkTo(MedicoController.class).slash(medicoId).withSelfRel();
			a.add(self);
		});
		return ResponseEntity.ok(dtos);
	}

}
