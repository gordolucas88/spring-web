package br.com.symbiosys.regescweb.controllers;

import br.com.symbiosys.regescweb.dto.ProfessorDto;
import br.com.symbiosys.regescweb.models.Professor;
import br.com.symbiosys.regescweb.models.StatusProfessor;
import br.com.symbiosys.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;



    @GetMapping("/professores")
    public ModelAndView index(){

        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professores/new")
    public ModelAndView newProfessor(ProfessorDto requisicao) {

        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());



        return mv;
    }

    @PostMapping("/professores")
    public ModelAndView create(@Valid ProfessorDto requisicao, BindingResult result){

        if(result.hasErrors()){
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv ;
        } else {

            Professor professor = requisicao.toProfessor();
            System.out.println(requisicao);
            System.out.println(professor);

            this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores");
        }

    }
}
