package com.cursomarajoara.sisgec.repository.helper.curso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;

public class CursosImpl implements CursosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Curso> filtrar(CursoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}			
			
			if(isDisciplinaPresente(filtro)) {
				criteria.add(Restrictions.eq("disciplina", filtro.getDisciplina()));
			}
			
			if(isTipoPresente(filtro)) {
				criteria.add(Restrictions.eq("tipoCurso", filtro.getTipoCurso()));
			}
			
			if(filtro.getTurno() != null) {
				criteria.add(Restrictions.eq("turno", filtro.getTurno()));
			}
			
			
		}
		return criteria.list();
	}
	
	private boolean isDisciplinaPresente(CursoFilter filtro) {
		return filtro.getDisciplina() != null && filtro.getDisciplina().getCodigo_disciplina() != null;
	}
	
	private boolean isTipoPresente(CursoFilter filtro) {
		return filtro.getTipoCurso() != null && filtro.getTipoCurso().getCodigo_tipoCurso() != null;
	}
}