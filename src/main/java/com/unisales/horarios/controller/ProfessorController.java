package com.unisales.horarios.controller;

import com.unisales.horarios.model.Professor;
import com.unisales.horarios.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("professores", professorService.findAll());
        return "professores/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professores/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorService.findById(id));
        return "professores/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("professor") Professor professor,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "professores/form";
        }
        professorService.save(professor);
        return "redirect:/professores";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        professorService.deleteById(id);
        return "redirect:/professores";
    }
}
