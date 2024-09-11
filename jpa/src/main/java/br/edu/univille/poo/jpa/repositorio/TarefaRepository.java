package br.edu.univille.poo.jpa.repositorio;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findAllByTituloContaining(String titulo);


    List<Tarefa> findAllByFinalizado(boolean finalizado);


    List<Tarefa> findAllByPrevisaoFinalizar(LocalDate previsaoDataFinalizar);
}