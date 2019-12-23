package com.cleison.myfinance.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyFinanceExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
 
	/**
	 * 
	 * Método sobrescrito é chamado quando há problema de serialização. Ex:
	 * 
	 * Foi enviado um JSON com mais propriedades do que realmente existe na Classe correspondente.
	 * A api jackson irá validar e será executado esse método para o tratamento da exceção.
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemCliente = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemCliente, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, headers, status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros = criarListaErro(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	private List<Erro> criarListaErro(BindingResult bindingResult) {
		
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldErro : bindingResult.getFieldErrors()) {
			
			String mensagemCliente = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldErro.toString();//mais detalhado para o desenvolvedor
			
			erros.add(new Erro(mensagemCliente, mensagemDesenvolvedor));
			
		}
		
		return erros;
		
	}
	
	public static class Erro {
		
		private String mensagemCliente;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemCliente, String mensagemDesenvolvedor) {
			super();
			this.mensagemCliente = mensagemCliente;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemCliente() {
			return mensagemCliente;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
	}
	
}
