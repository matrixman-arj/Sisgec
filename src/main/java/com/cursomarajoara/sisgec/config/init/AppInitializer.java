package com.cursomarajoara.sisgec.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cursomarajoara.sisgec.config.JPAConfig;
import com.cursomarajoara.sisgec.config.MailConfig;
import com.cursomarajoara.sisgec.config.SecurityConfig;
import com.cursomarajoara.sisgec.config.ServiceConfig;
import com.cursomarajoara.sisgec.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {		
		return new Class<?>[] { WebConfig.class, MailConfig.class};
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
        return new Filter[] { httpPutFormContentFilter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
