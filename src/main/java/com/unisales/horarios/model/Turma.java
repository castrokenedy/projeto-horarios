package com.unisales.horarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da turma é obrigatório")
    @Column(nullable = false, length = 80)
    private String nome;

    @NotBlank(message = "O turno é obrigatório")
    @Column(nullable = false, length = 20)
    private String turno; // Ex: Manhã, Tarde, Noite

    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Horario> horarios = new ArrayList<>();

    public Turma() {
    }

    public Turma(String nome, String turno) {
        this.nome = nome;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
