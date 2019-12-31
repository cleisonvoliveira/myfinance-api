package com.cleison.myfinance.api.repository.lancamento;

import java.util.List;

import com.cleison.myfinance.api.model.Lancamento;
import com.cleison.myfinance.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
