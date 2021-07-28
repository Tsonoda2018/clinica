package br.com.santander.clinica.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.dto.EspecialidadeDto;
import br.com.santander.clinica.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

	private final EspecialidadeService especialidadeService;

	public EspecialidadeController(EspecialidadeService especialidadeService) {
		this.especialidadeService = especialidadeService;
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Especialidade especialidade,
			UriComponentsBuilder uriBuilder) {
		Especialidade especialidadeSalva = especialidadeService.salvar(especialidade);
		URI uri = uriBuilder.path("/especialidades/{id}")
				.buildAndExpand(especialidadeSalva.getid()).toUri();
		return ResponseEntity.created(uri).body((RepresentationModel.of(EspecialidadeDto.converte(especialidadeSalva))));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EspecialidadeDto> buscarPorId(@PathVariable Integer id) {
		EspecialidadeDto especialidadeDto = EspecialidadeDto.converte(especialidadeService.buscarPorId(id));
		Link self = linkTo(EspecialidadeController.class).slash(id).withSelfRel();
		Link especialidades = linkTo(EspecialidadeController.class).withRel("especialidades");
		return ResponseEntity.ok(especialidadeDto.add(self).add(especialidades));

	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		List<EspecialidadeDto> dtos = especialidadeService.buscarTodos().stream().map(e -> {
			EspecialidadeDto dto = EspecialidadeDto.converte(e);
			Link self = linkTo(EspecialidadeController.class).slash(e.getid()).withSelfRel();
			Link especialidades = linkTo(EspecialidadeController.class).withRel("especialidades");
			dto.add(self).add(especialidades);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(RepresentationModel.of(dtos));
	}
}
