package com.cleison.myfinance.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleison.myfinance.api.exception.PessoaInexistenteOuInativaException;
import com.cleison.myfinance.api.model.Lancamento;
import com.cleison.myfinance.api.model.Pessoa;
import com.cleison.myfinance.api.repository.LancamentoRepository;
import com.cleison.myfinance.api.repository.PessoaRepository;

/**
 * @author cleison.oliveira
 *
 */
@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
}
