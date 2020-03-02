package com.cleison.myfinance.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cleison.myfinance.api.config.property.MyFinanceApiProperty;

/**
 * @author cleison.oliveira
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(MyFinanceApiProperty.class)
public class MyfinanceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfinanceApiApplication.class, args);
	}

}
