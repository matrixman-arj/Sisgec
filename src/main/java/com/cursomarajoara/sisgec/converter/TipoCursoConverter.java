package com.cursomarajoara.sisgec.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.TipoCurso;

public class TipoCursoConverter implements Converter<String, TipoCurso> {

	@Override
	public TipoCurso convert(String codigo_tipoCurso) {
		if (!StringUtils.isEmpty(codigo_tipoCurso)) {
			TipoCurso tipoCurso = new TipoCurso();
			tipoCurso.setCodigo_tipoCurso(Long.valueOf(codigo_tipoCurso));
			return tipoCurso;
		}
		
		return null;
	}

}
