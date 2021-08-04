package br.com.santander.clinica.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.spec.internal.HttpStatus;

import br.com.santander.clinica.model.Especialidade;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.dto.MedicoDto;
import br.com.santander.clinica.service.EspecialidadeService;
import br.com.santander.clinica.service.MedicoService;
import io.restassured.http.ContentType;

@WebMvcTest
public class MedicoControllerTest {

	@Autowired
	private MedicoController medicoController;

	@MockBean
	private PacienteController pacienteController;
	@MockBean
	private MedicoService medicoService;
	@MockBean
	private EspecialidadeService especialidadeService;
	@MockBean
	private MedicoDto medicoDto;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.medicoController);
	}

	@Test
	public void deveRetornarOkConsultarPorId() {
		Medico medico = new Medico("Medico1", "123", LocalDate.now(), "12315", new Especialidade("Oftalmologista"));
		when(this.medicoService.buscarPorId(1)).thenReturn(medico);
		given().accept(ContentType.JSON).when().get("/medicos/{id}", 1).then().statusCode(HttpStatus.OK);
	}

	@Test
	public void deveRetornarCreatedSalvar() {
		Medico medico = new Medico("Medico1", "123", LocalDate.now(), "12315", new Especialidade("Oftalmologista"));
		medico.setId(1);
//		when(especialidadeService.buscarPorId(1)).thenReturn(new Especialidade("Oftalmologista"));
		when(this.medicoService.salvar(medico)).thenReturn(medico);
		
		given().body("{\r\n"
				+ "    \"nome\": \"Joao\",\r\n"
				+ "    \"cpf\": \"123456489\",\r\n"
				+ "    \"dataNascimento\": \"2000-12-03\",\r\n"
				+ "    \"enderecos\": [\r\n"
				+ "        {\r\n"
				+ "            \"rua\": \"rua3\",\r\n"
				+ "            \"numero\": \"2\",\r\n"
				+ "            \"cidade\": \"São Paulo\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"crm\": \"123456789\",\r\n"
				+ "    \"idEspecialidade\": 1\r\n"
				+ "}\r\n"
				+ "}").contentType("application/JSON").post("/medicos").then().log().all().assertThat().statusCode(201);
		
	}
}
