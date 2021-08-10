package br.com.santander.clinica.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.santander.clinica.model.Agenda;

public class AgendaSpecification {
	
	public static Specification<Agenda> porNomeMedico(String nomeMedico) {
		return (root, query, builder) -> nomeMedico != null ? builder.like(root.join("agendaId").join("medico").get("nome"), "%" + nomeMedico + "%")  : null;
	}

	public static Specification<Agenda> porNomePaciente(String nomePaciente) {
		return (root, query, builder) -> nomePaciente != null ? builder.like(root.join("paciente").get("nome"), "%" + nomePaciente + "%")  : null;
	}
	
	public static Specification<Agenda> porEspecialidade(String especialidade) {
		return (root, query, builder) -> especialidade != null ? builder.like(root.join("agendaId").join("medico").join("especialidade").get("nome"), "%" + especialidade + "%")  : null;
	}

}
