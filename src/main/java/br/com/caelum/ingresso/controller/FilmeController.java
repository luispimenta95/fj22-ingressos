package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import br.com.caelum.ingresso.model.form.FilmeForm;
import br.com.caelum.ingresso.model.form.SessaoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;

@Autowired
private GeneroDao gd;
    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, FilmeForm filmeForm){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
        	
        	Filme  filme = filmeDao.findOne(id.get());
        	filmeForm.fromFilme(filme);
        }

        List<Genero> generos = gd.findAll();
        modelAndView.addObject("generos",generos);
        modelAndView.addObject("filme",filmeForm);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid FilmeForm filmeForm, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filmeForm.getId()), filmeForm);
        }

        Filme filme=filmeForm.toFilme(gd);
        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }
    @RequestMapping("filme/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Filme> result = filmeDao.BuscaNomes(keyword);
        	
        
   
        
        
        ModelAndView mav = new ModelAndView("filme/search");
        mav.addObject("result", result);
     
        return mav;    
    }

    
    
    
    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

}
