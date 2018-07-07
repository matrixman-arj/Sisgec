package com.cursomarajoara.sisgec.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cursomarajoara.sisgec.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

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


	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao tentar salvar a foto na pasta temporária", e);
			}			
		}
		return novoNome;
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária", e );
		}
	}
	
	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar mover foto para o destino final! ", e);
		}	
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(50, 50).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar gerar thumbnail", e);
		}
		
	}
	
	@Override
	public byte[] recuperarFoto(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto", e );
		}	
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
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original: %s, novo nome: %s", nomeOriginal, novoNome));
		}
		return novoNome;
	}	

}
