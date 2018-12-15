package com.cursomarajoara.sisgec.repository.helper.aluno;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.filter.AlunoFilter;
import com.cursomarajoara.sisgec.repository.paginacao.PaginacaoUtil;

public class AlunosImpl implements AlunosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Aluno> filtrar(AlunoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("endereco.cidade", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("c.estado", "e", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(AlunoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}


	private void adicionarFiltro(AlunoFilter filtro, Criteria criteria) {
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}			
			
			if(!StringUtils.isEmpty(filtro.getDocReceita())) {
				criteria.add(Restrictions.eq("docReceita", filtro.getCpfOuCnpjSemFormatacao()));
			}			
			
		}
	}	
}
