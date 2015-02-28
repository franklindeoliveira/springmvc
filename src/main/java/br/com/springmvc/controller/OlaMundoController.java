package br.com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("/olaMundoSpringMVC")
	public String execute() {
		System.out.println("Ola mundo Spring MVC!");
		return "home";
	}
	
}
