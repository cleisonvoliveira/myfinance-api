package com.cleison.myfinance.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.cleison.myfinance.api.model.Lancamento;
import com.cleison.myfinance.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//criar ads restrições
		Predicate[] predicages = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicages);
		
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	//TODO: Tentar usar o static MetaModel do JPA e descartar as strings de atributos.
	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			Predicate descricaoPred = builder.like(builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao() + "%");
			predicates.add(descricaoPred);
		}
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoDe())) {
			Predicate dataVencDePred = builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe());
			predicates.add(dataVencDePred);
		}
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoAte())) {
			Predicate dataVencAtePred = builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte());
			predicates.add(dataVencAtePred);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
