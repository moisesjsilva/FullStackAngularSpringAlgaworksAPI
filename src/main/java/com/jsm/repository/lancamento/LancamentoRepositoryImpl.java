package com.jsm.repository.lancamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.jsm.dto.lancamento.LancamentoEstatisticaCategoriaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaDiaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaPessoaDTO;
import com.jsm.model.Lancamento;
import com.jsm.model.Lancamento_;
import com.jsm.model.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		// CRIA RESTRIÇÕES
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = em.createQuery(criteria);

		AdicionarRestricaoPaginacaoQuery(query,pageable);
		
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Long total(LancamentoFilter filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		// CRIA RESTRIÇÕES
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return em.createQuery(criteria).getSingleResult();
	}

	private void AdicionarRestricaoPaginacaoQuery(TypedQuery<Lancamento> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + filter.getDescricao().toLowerCase() + "%"));
		}

		if (filter.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));

		}

		if (filter.getDataVencimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoAte()));

		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<LancamentoEstatisticaCategoriaDTO> porCategoria(LocalDate mesReferncia) {
		CriteriaBuilder builder =em.getCriteriaBuilder();
		CriteriaQuery<LancamentoEstatisticaCategoriaDTO> criteriaQuery = builder.createQuery(LancamentoEstatisticaCategoriaDTO.class);
	    Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
	    
	    criteriaQuery.select(builder.construct(
	    		LancamentoEstatisticaCategoriaDTO.class, 
	    		root.get(Lancamento_.categoria),
	    		builder.sum(root.get(Lancamento_.valor))));
	    
	    LocalDate inicio = mesReferncia.withDayOfMonth(1);// Pega primeiro dia do mes
	    LocalDate fim = mesReferncia.withDayOfMonth(mesReferncia.lengthOfMonth());// Pega último dia do mês
	    
	    criteriaQuery.where(
	    		builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				inicio),
	    		builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				fim)
	    		);
	    
	    criteriaQuery.groupBy(root.get(Lancamento_.categoria));
	    
	    TypedQuery<LancamentoEstatisticaCategoriaDTO> result = em.createQuery(criteriaQuery);
	    
		return result.getResultList();
	}
	
	
	@Override
	public List<LancamentoEstatisticaDiaDTO> porDia(LocalDate mesReferncia) {
		CriteriaBuilder builder =em.getCriteriaBuilder();
		CriteriaQuery<LancamentoEstatisticaDiaDTO> criteriaQuery = builder.createQuery(LancamentoEstatisticaDiaDTO.class);
	    Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
	    
	    criteriaQuery.select(builder.construct(
	    		LancamentoEstatisticaDiaDTO.class, 
	    		root.get(Lancamento_.tipo),
	    		root.get(Lancamento_.dataVencimento),
	    		builder.sum(root.get(Lancamento_.valor))));
	    
	    LocalDate inicio = mesReferncia.withDayOfMonth(1);// Pega primeiro dia do mes
	    LocalDate fim = mesReferncia.withDayOfMonth(mesReferncia.lengthOfMonth());// Pega último dia do mês
	    
	    criteriaQuery.where(
	    		builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				inicio),
	    		builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				fim)
	    		);
	    
	    criteriaQuery.groupBy(root.get(Lancamento_.tipo),root.get(Lancamento_.dataVencimento));
	    
	    TypedQuery<LancamentoEstatisticaDiaDTO> result = em.createQuery(criteriaQuery);
	    
		return result.getResultList();
	}
	
	
	@Override
	public List<LancamentoEstatisticaPessoaDTO> porPessoa(LocalDate inicio, LocalDate fim) {
		CriteriaBuilder builder =em.getCriteriaBuilder();
		CriteriaQuery<LancamentoEstatisticaPessoaDTO> criteriaQuery = builder.createQuery(LancamentoEstatisticaPessoaDTO.class);
	    Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
	    //LancamentoEstatisticaPessoaDTO(LancamentoTipo tipo, Pessoa pessoa, BigDecimal total) {
	    criteriaQuery.select(builder.construct(
	    		LancamentoEstatisticaPessoaDTO.class, 
	    		root.get(Lancamento_.tipo),
	    		root.get(Lancamento_.pessoa),
	    		builder.sum(root.get(Lancamento_.valor))));
	    
//	    LocalDate inicio = mesReferncia.withDayOfMonth(1);// Pega primeiro dia do mes
//	    LocalDate fim = mesReferncia.withDayOfMonth(mesReferncia.lengthOfMonth());// Pega último dia do mês
//	    
	    criteriaQuery.where(
	    		builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				inicio),
	    		builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), 
	    				fim)
	    		);
	    
	    criteriaQuery.groupBy(root.get(Lancamento_.tipo),root.get(Lancamento_.pessoa));
	    
	    TypedQuery<LancamentoEstatisticaPessoaDTO> result = em.createQuery(criteriaQuery);
	    
		return result.getResultList();
	}

}
