package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;


@Controller
public class GeneroController {


    @Autowired
    private GeneroDao generoDao;
    
    @Autowired
    private FilmeDao fd;

    @GetMapping({"/admin/genero", "/admin/genero/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Genero genero){

        ModelAndView modelAndView = new ModelAndView("genero/genero");

        if (id.isPresent()){
            genero = generoDao.findOne(id.get());
        }

        modelAndView.addObject("genero", genero);

        return modelAndView;
    }


    @PostMapping("/admin/genero")
    @Transactional
    public ModelAndView salva(@Valid Genero genero, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(genero.getId_genero()), genero);
        }

        generoDao.save(genero);

        ModelAndView view = new ModelAndView("redirect:/admin/generos");

        return view;
    }


    @GetMapping(value="/admin/generos")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("genero/lista");

        modelAndView.addObject("generos", generoDao.findAll());

        return modelAndView;
    }
    
    @RequestMapping("genero/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Genero> result = generoDao.BuscaNomes(keyword);
        	
        
   
        
        
        ModelAndView mav = new ModelAndView("genero/search");
        mav.addObject("result", result);
     
        return mav;    
    }



    @DeleteMapping("/admin/genero/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        generoDao.delete(id);
    }
    @GetMapping("/genero/lista2")
	public ModelAndView formSessao(@RequestParam("Id_genero") Integer genero) {
		
    	Genero g = generoDao.findOne(genero);
    	List<Filme> filmes = fd.buscaFilmeGenero(g);
		ModelAndView mav = new ModelAndView("genero/lista2");
		mav.addObject("filmes", fd.buscaFilmeGenero(g));
		mav.addObject("nome_genero", g.getNome());

		
		return mav;
	}
    
}
