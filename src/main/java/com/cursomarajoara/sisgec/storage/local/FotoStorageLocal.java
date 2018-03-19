package com.cursomarajoara.sisgec.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cursomarajoara.sisgec.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class); 
	
	private Path local;
	private Path localTemporario;
	
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".sisgecfotos"));
		
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}


	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pastas criadas com sucesso!");
				logger.debug("Pasta defualt: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporário: " + this.localTemporario.toAbsolutePath());
			}
			
		} catch (IOException e) {			
			throw new RuntimeException("Erro ao tentar criar pasta salvar foto", e);
		}
		
	}


	@Override
	public void salvarTemporariamente(MultipartFile[] files) {
		System.out.println(">>> Salvando a foto temporariamente...");
	}

}
