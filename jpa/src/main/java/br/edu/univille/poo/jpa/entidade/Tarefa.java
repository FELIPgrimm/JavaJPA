package br.edu.univille.poo.jpa.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column
    private String descricao;
    @Column
    private boolean finalizado;
    @Column(nullable = false)
    private LocalDate previsaoFinalizar;
    @Column
    private LocalDate dataFinalizacao;

}

