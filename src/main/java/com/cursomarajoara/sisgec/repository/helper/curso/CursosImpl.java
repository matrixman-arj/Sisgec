package com.cursomarajoara.sisgec.repository.helper.curso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.dto.CursoDTO;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.CursoDisciplina;
import com.cursomarajoara.sisgec.model.Disciplina;
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
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(CursoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Curso buscarComDisciplinas(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		criteria.createAlias("disciplinas", "d", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Curso) criteria.uniqueResult();
	}


	private void adicionarFiltro(CursoFilter filtro, Criteria criteria) {
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}			
			
			criteria.createAlias("disciplinas", "d", JoinType.LEFT_OUTER_JOIN);
			if(filtro.getDisciplinas() != null && ! filtro.getDisciplinas().isEmpty()) {
				List<Criterion> subqueries = new ArrayList<>();
				
				for (Long codigoDisciplina : filtro.getDisciplinas().stream().mapToLong(Disciplina::getCodigo).toArray()) {
					
					DetachedCriteria dc = DetachedCriteria.forClass(CursoDisciplina.class);
					dc.add(Restrictions.eqOrIsNull("id.disciplina.codigo", codigoDisciplina));
					dc.setProjection(Projections.property("id.curso"));
					subqueries.add(Subqueries.propertyIn("codigo", dc));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
				
			}
			
			if(isTipoPresente(filtro)) {
				criteria.add(Restrictions.eq("tipoCurso", filtro.getTipoCurso()));
			}
			
			if(filtro.getTurno() != null) {
				criteria.add(Restrictions.eq("turno", filtro.getTurno()));
			}
			
			
		}
	}
		
//	private boolean isDisciplinaPresente(CursoFilter filtro) {
//		return filtro.getDisciplina() != null && filtro.getDisciplina().getCodigo() != null;
//	}
	
	private boolean isTipoPresente(CursoFilter filtro) {
		return filtro.getTipoCurso() != null && filtro.getTipoCurso().getCodigo() != null;
	}

	@Override
	public List<CursoDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new com.cursomarajoara.sisgec.dto.CursoDTO(codigo, sku, nome, valor, foto) "
				+ "from Curso where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		List<CursoDTO> cursosFitrados = manager.createQuery(jpql, CursoDTO.class)
				.setParameter("skuOuNome", skuOuNome + "%")
				.getResultList();
				
		return cursosFitrados;
	}	
}
