package br.edu.univille.poo.jpa.servico;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import br.edu.univille.poo.jpa.repositorio.TarefaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServico {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> obterTodos() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> obterPeloId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa incluir(Tarefa tarefa) {
        tarefa.setId(0L);
        if (Strings.isBlank(tarefa.getTitulo())) {
            throw new RuntimeException("Titulo não informado.");
        }
        if (Strings.isBlank(String.valueOf(tarefa.getPrevisaoFinalizar()))) {
            throw new RuntimeException("Previsão de finalizar não informada.");
        }

        if (!tarefaRepository.findAllByTituloContaining(tarefa.getTitulo()).isEmpty()) {
            throw new RuntimeException("Titulo já está cadastrado.");
        }
        tarefa = tarefaRepository.save(tarefa);
        return tarefa;
    }

    public Tarefa atualizar(Tarefa tarefa) {
        Tarefa antigo = tarefaRepository.findById(tarefa.getId()).orElse(null);
        if (antigo == null) {
            throw new RuntimeException("Tarefa não foi encontrada.");
        }
        antigo.setTitulo(tarefa.getTitulo());
        antigo.setDescricao(tarefa.getDescricao());
        antigo.setFinalizado(tarefa.isFinalizado());
        antigo.setDataFinalizacao(tarefa.getDataFinalizacao());
        antigo.setPrevisaoFinalizar(tarefa.getPrevisaoFinalizar());
        if (Strings.isBlank(tarefa.getTitulo())) {
            throw new RuntimeException("Titulo não informado.");
        }
        if (Strings.isBlank(String.valueOf(tarefa.getPrevisaoFinalizar()))) {
            throw new RuntimeException("Previsão de finalizar não informada.");
        }

        for (var p : tarefaRepository.findAllByTituloContaining(tarefa.getTitulo())) {
            if (antigo.getId() != p.getId()) {
                throw new RuntimeException("Titulo já está cadastrado.");
            }
        }
        return tarefaRepository.save(antigo);
    }

    public void excluir(Tarefa tarefa) {
        var antigo = tarefaRepository.findById(tarefa.getId()).orElse(null);
        if (antigo == null) {
            throw new RuntimeException("Tarefa não encontrada.");
        }
        tarefaRepository.delete(antigo);
    }
}
