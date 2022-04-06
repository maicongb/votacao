package br.gov.df.pm.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
	ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = { FileSizeValidator.class })
public @interface FileSize {

	String message() default "tamanho do arquivo inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String max();
	
}