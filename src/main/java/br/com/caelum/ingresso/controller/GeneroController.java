package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class GeneroController {


    @Autowired
    private GeneroDao generoDao;


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


    @DeleteMapping("/admin/genero/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        generoDao.delete(id);
    }

}
