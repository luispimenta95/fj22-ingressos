package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.form.LugarForm;

@Controller

public class SessaoController {
	@Autowired
	private FilmeDao fd;
	
	@Autowired
	private SalaDao sd;
	
	@GetMapping("/admin/sessao")
	public ModelAndView formSessao(@RequestParam("salaId")Integer salaId) {
		List<Filme> filmes = fd.findAll();
		ModelAndView mav=new ModelAndView("sessao/sessao");
		mav.addObject("filmes",fd.findAll());
		mav.addObject("sala",sd.findOne(salaId));
		return mav;
	}
}
