package com.cleison.myfinance.api.service;

import org.springframework.beans.BeanUtils;
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
		validarPessoa(lancamento.getPessoa());
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		
		if(!lancamento.getPessoa().getCodigo().equals(lancamentoSalvo.getPessoa().getCodigo())) {
			validarPessoa(lancamento.getPessoa());
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Pessoa pessoa) {
		
		Pessoa pessoaEncontrada = pessoaRepository.findById(pessoa.getCodigo()).orElse(null);
		if(pessoaEncontrada == null || pessoaEncontrada.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Lancamento buscarLancamentoExistente(Long codigo) {
		return lancamentoRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
	}
	
}
