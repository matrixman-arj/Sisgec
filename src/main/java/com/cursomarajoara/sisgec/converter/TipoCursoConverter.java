package com.cursomarajoara.sisgec.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.TipoCurso;

public class TipoCursoConverter implements Converter<String, TipoCurso> {

	@Override
	public TipoCurso convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			TipoCurso tipoCurso = new TipoCurso();
			tipoCurso.setCodigo(Long.valueOf(codigo));
			return tipoCurso;
		}		
		return null;
	}
	
//	@Override
//	public TipoCurso convert(Long nome) {
//		if (!StringUtils.isEmpty(nome)) {
//			TipoCurso tipoCurso = new TipoCurso();
//			tipoCurso.setNome(String.valueOf(nome));
//			return tipoCurso;
//		}		
//		return null;
//	}

}
