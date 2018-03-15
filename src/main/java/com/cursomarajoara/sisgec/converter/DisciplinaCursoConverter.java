package com.cursomarajoara.sisgec.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Disciplina;

public class DisciplinaCursoConverter implements Converter<String, Disciplina> {

	@Override
	public Disciplina convert(String codigo_disciplina) {
		if (!StringUtils.isEmpty(codigo_disciplina)) {
			Disciplina disciplina = new Disciplina();
			disciplina.setCodigo_disciplina(Long.valueOf(codigo_disciplina));
			return disciplina;
		}
		
		return null;
	}

}
