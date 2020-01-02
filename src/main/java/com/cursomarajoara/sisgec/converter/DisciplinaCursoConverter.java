package com.cursomarajoara.sisgec.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Disciplina;

public class DisciplinaCursoConverter implements Converter<String, Disciplina> {

	@Override
	public Disciplina convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Disciplina disciplina = new Disciplina();
			disciplina.setCodigo(Long.valueOf(codigo));
			return disciplina;
		}
		
		return null;
	}

}
