package com.example.gerenciador_tarefas.tarefa;

import com.example.gerenciador_tarefas.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    List<Tarefa> findByStatus(Status status);

    List<Tarefa> findByProjetoId(Long projetoId);
}
