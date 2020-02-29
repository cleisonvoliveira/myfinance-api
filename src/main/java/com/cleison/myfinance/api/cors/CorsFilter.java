package com.cleison.myfinance.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cleison.myfinance.api.config.property.MyFinanceApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

	@Autowired
	private MyFinanceApiProperty myFinanceApiProperties;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", myFinanceApiProperties.getOriginPermitido()); 
		response.setHeader("Access-Control-Allow-Credentials", "true"); 
		
		//Se a originPermitido for a mesma Origin que veio do browser e se for uma requisicao OPTIONS, entao sera permitido.
		if("OPTIONS".equals(request.getMethod()) && myFinanceApiProperties.getOriginPermitido().equals(request.getHeader("Origin"))) {
			
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE,PUT,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Max-Age", "3600");//60 minutos
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, resp); //segue a vida rs
		}
	}

}
