package br.com.symbiosys.regescweb.controllers;

import br.com.symbiosys.regescweb.dto.ProfessorDto;
import br.com.symbiosys.regescweb.models.Professor;
import br.com.symbiosys.regescweb.models.StatusProfessor;
import br.com.symbiosys.regescweb.repositories.ProfessorRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;



    @GetMapping("")
    public ModelAndView index(){

        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newProfessor(ProfessorDto requisicao) {

        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());



        return mv;
    }

    @PostMapping("")
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
            return new ModelAndView("redirect:/professores/" + professor.getId());
        }

    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){

        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){

            Professor professor = optional.get();


            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);

            return mv;


        } else {
            return new ModelAndView("redirect:/professores");
        }



    }
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, ProfessorDto professorDto){
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){

            Professor professor = optional.get();

            professorDto.fromProfessor(professor);


            ModelAndView mv = new ModelAndView("professores/edit");

            mv.addObject("professorId", professor.getId());
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;

        } else {
            return new ModelAndView("redirect:/professores");
        }

    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid ProfessorDto professorDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            mv.addObject("professorId", id);


            return mv;
        } else {

            Optional<Professor> optional = this.professorRepository.findById(id);

            if (optional.isPresent()) {
                Professor professor = professorDto.toProfessor(optional.get());
                this.professorRepository.save(professor);

                return new ModelAndView("redirect:/professores/" + professor.getId());
            } else {
                ModelAndView mv = new ModelAndView("redirect:/professores");
                mv.addObject("mensagem", "Professor #" + id + " não encontrado!");
                mv.addObject("erro", true);
                return mv;
            }
        }

    }
    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView mv = new  ModelAndView("redirect:/professores");
        try {
            this.professorRepository.deleteById(id);
            mv.addObject("mensagem", "Professor #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {

            mv.addObject("mensagem", "Professor #" + id + " não encontrado!");
            mv.addObject("erro", true);
        }

    return mv;

    }
}
