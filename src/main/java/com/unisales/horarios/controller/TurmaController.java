package com.unisales.horarios.controller;

import com.unisales.horarios.model.Turma;
import com.unisales.horarios.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", turmaService.findAll());
        return "turmas/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("turma", new Turma());
        return "turmas/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("turma", turmaService.findById(id));
        return "turmas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("turma") Turma turma,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "turmas/form";
        }
        turmaService.save(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        turmaService.deleteById(id);
        return "redirect:/turmas";
    }
}
