package br.com.symbiosys.regescweb.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello"); // arquivo que sera renderizado
        mv.addObject( "nome", "Lucas" );
        System.out.println("************");
        return mv;

    }

    @GetMapping("/hello-model")
    public String hello(Model model) {

        model.addAttribute( "nome", "Lucas" );
        System.out.println("************");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html

    }

    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request) {

        request.setAttribute( "nome", "Lucas" );
        System.out.println("************");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html

    }


}
