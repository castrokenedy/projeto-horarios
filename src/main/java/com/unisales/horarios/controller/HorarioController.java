package com.unisales.horarios.controller;

import com.unisales.horarios.model.DiaSemana;
import com.unisales.horarios.model.Horario;
import com.unisales.horarios.service.DisciplinaService;
import com.unisales.horarios.service.HorarioService;
import com.unisales.horarios.service.ProfessorService;
import com.unisales.horarios.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HorarioController {

    private final HorarioService horarioService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final TurmaService turmaService;

    @Autowired
    public HorarioController(HorarioService horarioService,
                              ProfessorService professorService,
                              DisciplinaService disciplinaService,
                              TurmaService turmaService) {
        this.horarioService = horarioService;
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    // ---------- CRUD ----------

    @GetMapping("/horarios")
    public String listar(Model model) {
        model.addAttribute("horarios", horarioService.findAll());
        return "horarios/list";
    }

    @GetMapping("/horarios/novo")
    public String novoForm(Model model) {
        model.addAttribute("horario", new Horario());
        carregarListasDeApoio(model);
        return "horarios/form";
    }

    @GetMapping("/horarios/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("horario", horarioService.findById(id));
        carregarListasDeApoio(model);
        return "horarios/form";
    }

    @PostMapping("/horarios/salvar")
    public String salvar(@Valid @ModelAttribute("horario") Horario horario,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            carregarListasDeApoio(model);
            return "horarios/form";
        }
        horarioService.save(horario);
        return "redirect:/horarios";
    }

    @GetMapping("/horarios/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        horarioService.deleteById(id);
        return "redirect:/horarios";
    }

    private void carregarListasDeApoio(Model model) {
        model.addAttribute("professores", professorService.findAll());
        model.addAttribute("disciplinas", disciplinaService.findAll());
        model.addAttribute("turmas", turmaService.findAll());
        model.addAttribute("diasSemana", DiaSemana.values());
    }

    // ---------- Visualizacao por professor ----------

    @GetMapping("/horarios/por-professor")
    public String porProfessor(@RequestParam(value = "professorId", required = false) Long professorId,
                                Model model) {
        model.addAttribute("professores", professorService.findAll());
        if (professorId != null) {
            model.addAttribute("professorSelecionado", professorService.findById(professorId));
            model.addAttribute("horarios", horarioService.findByProfessor(professorId));
        }
        return "horarios/por-professor";
    }

    // ---------- Visualizacao por turma ----------

    @GetMapping("/horarios/por-turma")
    public String porTurma(@RequestParam(value = "turmaId", required = false) Long turmaId,
                            Model model) {
        model.addAttribute("turmas", turmaService.findAll());
        if (turmaId != null) {
            model.addAttribute("turmaSelecionada", turmaService.findById(turmaId));
            model.addAttribute("horarios", horarioService.findByTurma(turmaId));
        }
        return "horarios/por-turma";
    }
}
