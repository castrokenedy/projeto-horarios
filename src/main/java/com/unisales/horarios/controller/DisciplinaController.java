package com.unisales.horarios.controller;

import com.unisales.horarios.model.Disciplina;
import com.unisales.horarios.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaService.findAll());
        return "disciplinas/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplinas/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("disciplina", disciplinaService.findById(id));
        return "disciplinas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("disciplina") Disciplina disciplina,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "disciplinas/form";
        }
        disciplinaService.save(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        disciplinaService.deleteById(id);
        return "redirect:/disciplinas";
    }
}
