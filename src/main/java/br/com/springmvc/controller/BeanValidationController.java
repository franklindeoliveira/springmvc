package br.com.springmvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.modelo.BeanValidation;

@Controller
public class BeanValidationController {
	
	@RequestMapping("/beanValidation")
	public String execute(@Valid BeanValidation beanValidation, BindingResult result) {
		if (result.hasErrors()) {
			return "beanValidation";
		}
		System.out.println(beanValidation.toString());
		return "beanValidation";
	}
}
