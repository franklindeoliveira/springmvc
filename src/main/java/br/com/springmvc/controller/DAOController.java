package br.com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.dao.DAO;

@Controller
public class DAOController {
	
	private DAO dao;

	@Autowired
	public DAOController(DAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/dao")
	public String conexaoEstabelecida() {
		dao.create();
		dao.read();
		dao.update();
		dao.delete();
		return "home";
	}

}