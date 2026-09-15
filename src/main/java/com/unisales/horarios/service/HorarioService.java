package com.unisales.horarios.service;

import com.unisales.horarios.model.Horario;
import com.unisales.horarios.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    @Autowired
    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    public Horario findById(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Horário não encontrado: " + id));
    }

    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void deleteById(Long id) {
        horarioRepository.deleteById(id);
    }

    public List<Horario> findByProfessor(Long professorId) {
        return horarioRepository.findByProfessorIdOrderByDiaSemanaAscHoraInicioAsc(professorId);
    }

    public List<Horario> findByTurma(Long turmaId) {
        return horarioRepository.findByTurmaIdOrderByDiaSemanaAscHoraInicioAsc(turmaId);
    }
}
