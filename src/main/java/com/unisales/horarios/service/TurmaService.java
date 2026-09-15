package com.unisales.horarios.service;

import com.unisales.horarios.model.Turma;
import com.unisales.horarios.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada: " + id));
    }

    public Turma save(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deleteById(Long id) {
        turmaRepository.deleteById(id);
    }
}
