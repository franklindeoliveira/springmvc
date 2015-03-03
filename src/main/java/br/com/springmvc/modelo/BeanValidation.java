package br.com.springmvc.modelo;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BeanValidation {

	@Size(min = 5, message = "{mensagem.erro.tamanhoMinimo}")
	private String parametro1;

	@NotNull(message = "{mensagem.erro.campoObrigatorio}")
	private Integer parametro2;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar parametro3;

	private Enum parametro4;

	public String getParametro1() {
		return parametro1;
	}

	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}

	public Integer getParametro2() {
		return parametro2;
	}

	public void setParametro2(Integer parametro2) {
		this.parametro2 = parametro2;
	}

	public Calendar getParametro3() {
		return parametro3;
	}

	public void setParametro3(Calendar parametro3) {
		this.parametro3 = parametro3;
	}

	public Enum getParametro4() {
		return parametro4;
	}

	public void setParametro4(Enum parametro4) {
		this.parametro4 = parametro4;
	}

	@Override
	public String toString() {
		return "BeanValidation [parametro1=" + parametro1 + ", parametro2="
				+ parametro2 + ", parametro3=" + parametro3 + ", parametro4="
				+ parametro4 + "]";
	}

}
