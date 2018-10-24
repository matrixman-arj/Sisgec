package com.cursomarajoara.sisgec.repository.helper.curso;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;
import com.cursomarajoara.sisgec.repository.paginacao.PaginacaoUtil;

public class CursosImpl implements CursosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(CursoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}


	private void adicionarFiltro(CursoFilter filtro, Criteria criteria) {
		
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
	}
		
	private boolean isDisciplinaPresente(CursoFilter filtro) {
		return filtro.getDisciplina() != null && filtro.getDisciplina().getCodigo_disciplina() != null;
	}
	
	private boolean isTipoPresente(CursoFilter filtro) {
		return filtro.getTipoCurso() != null && filtro.getTipoCurso().getCodigo_tipoCurso() != null;
	}
}
