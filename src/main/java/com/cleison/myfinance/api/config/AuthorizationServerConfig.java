package com.cleison.myfinance.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.cleison.myfinance.api.config.token.CustomTokenEnhancer;

@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("angular")
			.secret("@ngul@r0")
//			.secret("$2a$10$G1j5Rf8aEEiGc/AET9BA..xRR.qCpOUzBZoJd8ygbGy6tb3jsMT9G")
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")//refresh_token sera utilizado para nos fornecer um novo access token
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600*24)//tempo de vida do refresh_token. Nesse caso UM dia para expirar
		.and()
			.withClient("mobile")
			.secret("m0b1l30")
			//.secret("$2a$10$G/k88XLdbGbYDU1q1HNPgOLv7Ch0ETq6ImEfnqLZUKcxmP9TINeDG")
			.scopes("read")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600*24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhacerChain = new TokenEnhancerChain();
		tokenEnhacerChain.setTokenEnhancers(Arrays.asList(tokenEnhacerChain(), accessTokenConverter()));
		
		endpoints
			.tokenStore(tokenStore())//armazenando o token
			.tokenEnhancer(tokenEnhacerChain)
			.reuseRefreshTokens(Boolean.FALSE)//Para que a cada solicitacao um novo refresh_token seja enviado
			.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessToken = new JwtAccessTokenConverter();
		accessToken.setSigningKey("MyFinanceAPI");
		return accessToken;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhacerChain() {
		return new CustomTokenEnhancer();
	}
}
