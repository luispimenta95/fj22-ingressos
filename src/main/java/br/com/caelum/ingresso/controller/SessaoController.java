package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.testes.GerenciadorDeSessao;

@Controller

public class SessaoController {
	@Autowired
	private FilmeDao fd;

	@Autowired
	private SalaDao sd;

	@Autowired
	private SessaoDao sessD;

	@GetMapping("/admin/sessao")
	public ModelAndView formSessao(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		List<Filme> filmes = fd.findAll();
		ModelAndView mav = new ModelAndView("sessao/sessao");
		mav.addObject("filmes", fd.findAll());
		mav.addObject("sala", sd.findOne(salaId));
		mav.addObject("form", form);
		return mav;
	}

	@Transactional
	@PostMapping("/admin/sessao")
	public ModelAndView salvarSessao(@Valid SessaoForm form, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return formSessao(form.getSalaId(), form);

		}
		Sessao sessao = form.toSessao(sd, fd);
		List<Sessao> sessoes = sessD.buscaSessoes(sessao.getSala());
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		if (g.cabe(sessao)) {
			sessD.salva(sessao);
			ModelAndView view = new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes/");
			return view;

		}
		resultado.rejectValue("horario", "Horário em conflito", "Sessão tem conflitos com outra"); 
		return formSessao(form.getSalaId(), form);
	}

	@DeleteMapping("/admin/sessao/{id}")
	@ResponseBody
	@Transactional
	public void delete(@PathVariable("id") Integer id) {
		sessD.delete(id);
	}
}
