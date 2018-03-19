package com.cursomarajoara.sisgec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cursomarajoara.sisgec.service.CadastroCursoService;
import com.cursomarajoara.sisgec.storage.FotoStorage;
import com.cursomarajoara.sisgec.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCursoService.class )
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
