package com.cursomarajoara.sisgec.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.enuns.StatusVenda;
import com.cursomarajoara.sisgec.model.ItemVenda;
import com.cursomarajoara.sisgec.model.Venda;
import com.cursomarajoara.sisgec.repository.Vendas;

@Service
public class CadastroVendaService {
	
	@Autowired
	private Vendas vendas;
	
	@Transactional
	public void salvar(Venda venda) {
		if (venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}		
		
		if (venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega()
					, venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		vendas.save(venda);
	}
	
	@Transactional
	public void emitir(Venda venda) {
		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
		
	}
	
}
