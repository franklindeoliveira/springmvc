package br.com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.modelo.Bean;

@Controller
public class BindController {
	
	@RequestMapping("/bind")
	public String execute(Bean bean) {
		System.out.println(bean.toString());
		return "bind";
	}
}
