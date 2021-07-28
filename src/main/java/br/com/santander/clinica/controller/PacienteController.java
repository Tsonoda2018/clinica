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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.santander.clinica.model.Paciente;
import br.com.santander.clinica.model.dto.PacienteDto;
import br.com.santander.clinica.model.dto.PacienteInputDto;
import br.com.santander.clinica.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	private final PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		super();
		this.pacienteService = pacienteService;
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
		return ResponseEntity.ok(pacientesDto.add(self).add(pacientes));
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

//	@GetMapping("/especialidades/{id}")
//	public ResponseEntity<?> buscarPorEspecialidade(@PathVariable Integer id) {
//		List<PacienteDto> dtos = pacienteService.buscarPorEspecialidade(especialidadeService.buscarPorId(id)).stream()
//				.map(m -> {
//					PacienteDto dto = PacienteDto.converte(m);
//					Link self = linkTo(PacienteController.class).slash(m.getId()).withSelfRel();
//					Link pacientes = linkTo(PacienteController.class).withRel("pacientes");
//					dto.add(self).add(pacientes);
//					return dto;
//				}).collect(Collectors.toList());
//		return ResponseEntity.ok(dtos);
//	}
}
