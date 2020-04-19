package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.FilmeForm;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {

	@Autowired
	private FilmeDao filmeDao;

	@Autowired
	private GeneroDao gd;

	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private OmdbClient client;

	@GetMapping({ "/admin/filme", "/admin/filme/{id}" })
	public ModelAndView form(@PathVariable("id") Optional<Integer> id, FilmeForm filmeForm) {

		ModelAndView modelAndView = new ModelAndView("filme/filme");

		if (id.isPresent()) {

			Filme filme = filmeDao.findOne(id.get());
			filmeForm = new FilmeForm(filme);
		}

		List<Genero> generos = gd.findAll();
		modelAndView.addObject("generos", generos);
		modelAndView.addObject("filmeForm", filmeForm);

		return modelAndView;
	}

	@PostMapping("/admin/filme")
	@Transactional
	public ModelAndView salva(@Valid FilmeForm filmeForm, BindingResult result) {

		if (result.hasErrors()) {
			return form(Optional.ofNullable(filmeForm.getId()), filmeForm);
		}

		Filme filme = filmeForm.toFilme(gd);
		filmeDao.save(filme);

		ModelAndView view = new ModelAndView("redirect:/admin/filmes");

		return view;
	}

	@GetMapping(value = "/admin/filmes")
	public ModelAndView lista() {

		ModelAndView modelAndView = new ModelAndView("filme/lista");

		modelAndView.addObject("filmes", filmeDao.findAll());

		return modelAndView;
	}

	@RequestMapping("filme/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Filme> result = filmeDao.BuscaNomes(keyword);

		ModelAndView mav = new ModelAndView("filme/search");
		if (result.isEmpty()) {
			result = filmeDao.findAll();
			mav.addObject("msg", "Sua pesquisa não retornou nenhum resultado , por favor tente novamente");

		}

		mav.addObject("result", result);

		return mav;
	}

	@GetMapping("/filme/em-cartaz")
	public ModelAndView emCartaz() {
		ModelAndView modelAndView = new ModelAndView("filme/em-cartaz");
		modelAndView.addObject("filmes", filmeDao.findAll());
		return modelAndView;
	}

	@GetMapping("/filme/{id}/detalhe")
	public ModelAndView detalhes(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("/filme/detalhe");
		Filme filme = filmeDao.findOne(id);
		List<Sessao> sessoes = sessaoDao.buscaFilme(filme);
		Optional<DetalhesDoFilme> detalhesDoFilme = client.request(filme, DetalhesDoFilme.class);
		modelAndView.addObject("sessoes", sessoes);
		modelAndView.addObject("detalhes", detalhesDoFilme.orElse(new DetalhesDoFilme()));
		return modelAndView;
	}

	@DeleteMapping("/admin/filme/{id}")
	@ResponseBody
	@Transactional
	public void delete(@PathVariable("id") Integer id) {
		try {
		filmeDao.delete(id);
	
		}catch (Exception e) {
			System.out.println("Mensagem de erro");
		}
		}

}
