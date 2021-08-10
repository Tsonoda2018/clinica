package br.com.santander.clinica.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.santander.clinica.model.Medico;

public class MedicoSpecification {

	public static Specification<Medico> porNomeMedico(String nomeMedico) {
		  	 return (root, query, builder) -> nomeMedico != null ? builder.like(root.get("nome"), "%" + nomeMedico + "%")  : null;
	}
	public static Specification<Medico> porEspecialidade(String especialidade) {
		return (root, query, builder) -> especialidade != null ? builder.like(root.join("especialidade").get("nome"), "%" + especialidade + "%")  : null;
	}
}
