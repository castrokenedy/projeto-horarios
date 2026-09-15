# Sistema de Gerenciamento de Horários de Professores

Projeto da disciplina de **Back End** — ADS/UniSales. Sistema web em **Java + Spring Boot + Thymeleaf** para gestão de Professores, Disciplinas, Turmas e Horários.

## Equipe
- Kenedy Anderson Souza de Castro

## Stack
- Java 17
- Spring Boot 3.3.4 (Web, Data JPA, Thymeleaf, Validation)
- PostgreSQL (banco real)
- Maven

## Como rodar

1. Crie o banco no PostgreSQL:
   ```sql
   CREATE DATABASE horarios_db;
   ```
2. Ajuste usuário/senha em `src/main/resources/application.properties`.
3. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse: http://localhost:8080

O Hibernate cria as tabelas automaticamente (`ddl-auto=update`) na primeira execução.

## Estrutura do projeto

```
model/       -> Entidades JPA (Professor, Disciplina, Turma, Horario, DiaSemana)
repository/  -> Interfaces JpaRepository
service/     -> Regras de negócio
controller/  -> Rotas MVC (Controller + Thymeleaf)
templates/   -> Views HTML (Thymeleaf)
```

## Divisão do trabalho e fases de commit no GitHub

A ideia é cada fase virar uma branch (`feature/nome-da-fase`) e um Pull Request individual, para que a contribuição de cada integrante fique visível no histórico do repositório.

### Fase 1 — Setup do projeto (Kenedy)
Branch: `feature/setup-projeto`
- Criar o repositório, estrutura de pastas, `pom.xml`, `application.properties`
- Subir a classe principal (`HorariosApplication`) e o `.gitignore`
- Subir as 4 entidades (`model/`) já prontas com validações
- **Commit inicial de base para o time trabalhar em cima**

### Fase 2 — CRUD de Professores e Disciplinas (Ketleen)
Branch: `feature/crud-professor-disciplina`
- `ProfessorRepository`, `ProfessorService`, `ProfessorController` + telas (`professores/list.html`, `professores/form.html`)
- `DisciplinaRepository`, `DisciplinaService`, `DisciplinaController` + telas (`disciplinas/list.html`, `disciplinas/form.html`)
- Testar cadastro, edição e exclusão de ambas as entidades

### Fase 3 — CRUD de Turmas e Horários (Arthur)
Branch: `feature/crud-turma-horario`
- `TurmaRepository`, `TurmaService`, `TurmaController` + telas (`turmas/list.html`, `turmas/form.html`)
- `HorarioRepository`, `HorarioService`, `HorarioController` (parte de CRUD) + telas (`horarios/list.html`, `horarios/form.html`)
- Testar cadastro de horário vinculando professor, disciplina e turma

### Fase 4 — Visualizações e integração final (Kenedy)
Branch: `feature/visualizacao-por-professor-turma`
- Telas `horarios/por-professor.html` e `horarios/por-turma.html`
- Métodos `findByProfessor` / `findByTurma` no `HorarioRepository` e `HorarioService`
- Merge de todas as branches na `main`, resolução de conflitos, teste do fluxo completo

### Fase 5 — Revisão geral (todos)
- Cada integrante testa o fluxo completo (cadastro → visualização) e reporta bugs via Issues do GitHub
- Ajustes finais de UI e validações

### Fase 6 — Artigo científico (todos, em paralelo ao código)
- **Introdução** (problema resolvido): Kenedy
- **Desenvolvimento** (prints de tela + explicação do funcionamento): Kenedy Castro
- **Conclusão** + revisão final das normas Unisales + link do GitHub: Kenedy
- Formatação final e conversão para PDF: Kenedy

## Checklist de entrega
- [ ] Repositório público no GitHub com commits de todos os integrantes
- [ ] CRUD completo das 4 entidades funcionando
- [ ] Visualização por professor funcionando
- [ ] Visualização por turma funcionando
- [ ] Artigo científico (5–7 páginas, normas Unisales) em PDF
- [ ] Link do GitHub incluído no artigo
