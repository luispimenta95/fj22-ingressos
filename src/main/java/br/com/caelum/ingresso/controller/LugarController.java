package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.CriadorDeLugar;
import br.com.caelum.ingresso.model.form.LugarForm;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class LugarController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private LugarDao lugarDao;

	@Autowired
	private CriadorDeLugar cd;

	@GetMapping("/admin/lugar")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, LugarForm lugarDto) {

		lugarDto.setSalaId(salaId);

		ModelAndView view = new ModelAndView("lugar/lugar");

		view.addObject("lugarDto", lugarDto);

		return view;
	}

	@PostMapping("/admin/lugar")
    @Transactional
    public ModelAndView salva(@Valid LugarForm lugarDto, BindingResult result) {

    	if (result.hasErrors()) return form(lugarDto.getSalaId(), lugarDto);
    	cd.criaLugares(lugarDto.getFileira(), lugarDto.getPosicao(), lugarDto.getSalaId());
    	
    	return new ModelAndView("redirect:/admin/sala/"+lugarDto.getSalaId()+"/lugares/"); 
    	
	}
    

}
