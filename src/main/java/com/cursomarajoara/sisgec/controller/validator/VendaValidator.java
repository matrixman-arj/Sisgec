package com.cursomarajoara.sisgec.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cursomarajoara.sisgec.model.Venda;

@Component
public class VendaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		
		return Venda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "aluno.codigo", "",  " Selecione um aluno na pesquisa rápida");
				
		Venda venda = (Venda) target;
		validarSeInformouApenasHorarioEntrega(errors, venda);
		validarSeInformouItens(errors, venda);
		validarValorTotalNegativo(errors, venda);
		
	}

	private void validarValorTotalNegativo(Errors errors, Venda venda) {
		if (venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", " O valor total não pode ser negativo");
		}
	}

	private void validarSeInformouItens(Errors errors, Venda venda) {
		if(venda.getItens().isEmpty()) {
			errors.reject("", " Adicione ao menos um item na venda");
		}
	}

	private void validarSeInformouApenasHorarioEntrega(Errors errors, Venda venda) {
		if (venda.getHorarioEntrega() != null && venda.getDataEntrega() == null) {
			errors.rejectValue("dataEntrega", "", " Informe uma data para o horário");
		}
	}

}
