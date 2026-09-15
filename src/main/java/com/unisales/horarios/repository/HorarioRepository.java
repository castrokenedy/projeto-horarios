package com.unisales.horarios.repository;

import com.unisales.horarios.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByProfessorIdOrderByDiaSemanaAscHoraInicioAsc(Long professorId);

    List<Horario> findByTurmaIdOrderByDiaSemanaAscHoraInicioAsc(Long turmaId);
}
