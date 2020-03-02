/**
 * 
 */
package com.cleison.myfinance.api.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cleison.myfinance.api.model.Pessoa;

/**
 * @author cleison.oliveira
 *
 */
public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> pesquisar(String nome, Pageable pageable);

}
