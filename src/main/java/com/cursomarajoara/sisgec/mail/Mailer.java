package com.cursomarajoara.sisgec.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.ItemVenda;
import com.cursomarajoara.sisgec.model.Venda;
import com.cursomarajoara.sisgec.storage.FotoStorage;

@Component
public class Mailer {
	
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async
	public void enviar(Venda venda) {
		
		Context context = new Context(new Locale("pt", "BR"));
		context.setVariable("venda", venda);
		context.setVariable("logo", "logo");
		
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockCurso = false;
		for (ItemVenda item : venda.getItens()) {
			Curso curso = item.getCurso();
			if (curso.temFoto()) {
				String cid = "foto-" + curso.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, curso.getFoto() + "|" + curso.getContentType());
			}else {
				adicionarMockCurso = true;
				context.setVariable("mockCurso", "mockCurso");
			}
		}
		
		try {
			String email = thymeleaf.process("mail/ResumoVenda", context);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("matrixman.arj@nillgomes.com");
			helper.setTo(venda.getAluno().getEmail());
			helper.setSubject(String.format("Sisgec - Matricula nº %d", venda.getCodigo()));
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo.png"));
			
			if (adicionarMockCurso) {
				helper.addInline("mockCurso", new ClassPathResource("static/images/curso-mock.jpg"));
			}
			
			
			
			for (String cid : fotos.keySet()) {
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				helper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);				
			}
		
			mailSender.send(mimeMessage);
	} catch (MessagingException e) {
		logger.error("Erro ao tentar enviar o e-mail!", e);
	}
		
	}

}
