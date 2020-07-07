package com.DiVaioCifarelli.progetto_BD2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homepage(Model model) {
		ModelAndView page = new ModelAndView("Index.html");
		
		return page;
	}
	
	@RequestMapping(value="/film/search")
	public ModelAndView userByNome(Model model) {
		ModelAndView page = new ModelAndView("resultPage.html");
		return page;
	}
	
	@RequestMapping(value = "/prova", method = RequestMethod.GET)
	public ModelAndView prova(Model model) {
		ModelAndView page = new ModelAndView("prova.html");
		
		return page;
	}
}
