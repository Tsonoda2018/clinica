package br.com.santander.clinica.service;

import java.util.List;

import br.com.santander.clinica.model.Consulta;
import br.com.santander.clinica.model.Medico;
import br.com.santander.clinica.model.Paciente;

public interface ConsultaService {
	Consulta salvar(Consulta consulta);

	List<Consulta> buscarTodosPorPaciente(Paciente paciente);

	List<Consulta> buscarTodosPorMedico(Medico medico);

	Consulta buscarPorId(Integer id);

	void excluir(Integer id);
}
