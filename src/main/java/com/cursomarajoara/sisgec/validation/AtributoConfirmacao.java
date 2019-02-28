package com.cursomarajoara.sisgec.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.cursomarajoara.sisgec.validation.validator.AtributoConfirmacaoValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class })
public @interface AtributoConfirmacao {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default " Senha e confirmação não conferem!";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String atributo();
	
	String atributoConfirmacao();

}
