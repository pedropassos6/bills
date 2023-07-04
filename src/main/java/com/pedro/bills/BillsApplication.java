package com.pedro.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class BillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("en", "GB"));
	}

}
