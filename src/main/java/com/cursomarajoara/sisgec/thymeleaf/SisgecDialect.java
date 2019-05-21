package com.cursomarajoara.sisgec.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.cursomarajoara.sisgec.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.cursomarajoara.sisgec.thymeleaf.processor.MenuAttributeTagProcessor;
import com.cursomarajoara.sisgec.thymeleaf.processor.MessageElementTagProcessor;
import com.cursomarajoara.sisgec.thymeleaf.processor.OrderElementTagProcessor;
import com.cursomarajoara.sisgec.thymeleaf.processor.PaginationElementTagProcessor;

public class SisgecDialect extends AbstractProcessorDialect {

	public SisgecDialect() {
		super("Marajoara Sisgec", "sisgec", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
