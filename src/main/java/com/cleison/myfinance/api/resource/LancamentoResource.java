package com.cleison.myfinance.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleison.myfinance.api.model.Lancamento;
import com.cleison.myfinance.api.repository.LancamentoRepository;

@RequestMapping("/lancamentos")
@RestController
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@GetMapping
	public List<Lancamento> buscarLancamentos(){
		return lancamentoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Lancamento buscarPeloCodigo(@PathVariable Long codigo){
		return lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
}
