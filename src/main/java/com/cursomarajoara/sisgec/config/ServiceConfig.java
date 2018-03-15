package com.cursomarajoara.sisgec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cursomarajoara.sisgec.service.CadastroCursoService;

@Configuration
@ComponentScan(basePackageClasses = CadastroCursoService.class )
public class ServiceConfig {

}
