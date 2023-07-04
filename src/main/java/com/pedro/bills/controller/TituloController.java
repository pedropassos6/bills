package com.pedro.bills.controller;

import com.pedro.bills.model.StatusTitulo;
import com.pedro.bills.model.Titulo;
import com.pedro.bills.repository.Titulos;
import com.pedro.bills.repository.filter.TituloFilter;
import com.pedro.bills.service.CadastroTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private Titulos titulos;

    @Autowired
    private CadastroTituloService cadastroTituloService;

    @RequestMapping("/novo")
    public ModelAndView novo(){
        ModelAndView mv = new ModelAndView("CrudTitle");
        mv.addObject(new Titulo());
//        mv.addObject("todosStatusTitulo", StatusTitulo.values());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            return "CrudTitle";
        }
        try {
            cadastroTituloService.salvar(titulo);
            redirectAttributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso");
            return "redirect:/titulos/novo";
        } catch (DataIntegrityViolationException e){
            errors.rejectValue("dataVencimento", null, e.getMessage());
            return "CrudTitle";
        }
    }

    @RequestMapping
    public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro){
        List<Titulo> titulosBuscados = cadastroTituloService.filtrar(filtro);

        ModelAndView mv = new ModelAndView("PesquisaTitulos");

        mv.addObject("titulos", titulosBuscados);
        return mv;
    }

    @RequestMapping("{id}")
    public ModelAndView edicao(@PathVariable("id") Titulo titulo){
        ModelAndView mv = new ModelAndView("CrudTitle");
        mv.addObject(titulo);
        return mv;
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
        cadastroTituloService.excluir(id);

        attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
        return "redirect:/titulos";
    }

    @RequestMapping(value = "/{id}/receber", method = RequestMethod.PUT)
    public @ResponseBody String receber(@PathVariable Long id){
        return cadastroTituloService.receber(id);

    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo(){
        return Arrays.asList(StatusTitulo.values());
    }


}
