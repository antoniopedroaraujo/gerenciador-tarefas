package com.example.gerenciador_tarefas.tarefa;

import com.example.gerenciador_tarefas.Status;
import com.example.gerenciador_tarefas.exceptions.RecursoNaoEncontradoException;
import com.example.gerenciador_tarefas.projeto.ProjetoService;
import com.example.gerenciador_tarefas.responsavel.ResponsavelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefasService {
    private final TarefaRepository tarefaRepository;
    private final ProjetoService projetoService;
    private final ResponsavelService responsavelService;

    public TarefasService(
            TarefaRepository tarefaRepository,
            ProjetoService projetoService,
            ResponsavelService responsavelService
    ) {
        this.tarefaRepository = tarefaRepository;
        this.projetoService = projetoService;
        this.responsavelService = responsavelService;
    }

    public Tarefa criar(Tarefa tarefa) {

        // Verifica se o projeto existe
        tarefa.setProjeto(
                projetoService.buscarPorId(
                        tarefa.getProjeto().getId()
                )
        );

        // Verifica se foi informado responsável
        if (tarefa.getResponsavel() != null) {
            tarefa.setResponsavel(
                    responsavelService.buscarPorId(
                            tarefa.getResponsavel().getId()
                    )
            );
        }

        // Regras automáticas
        tarefa.setStatus(Status.NOVA);
        tarefa.setCriadaEm(LocalDateTime.now());

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listar(Status status, Long projetoId) {

        if (status != null) {
            return tarefaRepository.findByStatus(status);
        }

        if (projetoId != null) {
            return tarefaRepository.findByProjetoId(projetoId);
        }

        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Tarefa não encontrada: " + id
                        )
                );
    }

    public Tarefa atualizar(Long id, Tarefa dados) {

        Tarefa tarefa = buscarPorId(id);

        tarefa.setTitulo(dados.getTitulo());
        tarefa.setDescricao(dados.getDescricao());
        tarefa.setPrioridade(dados.getPrioridade());
        tarefa.setPrazo(dados.getPrazo());
        tarefa.setStatus(dados.getStatus());

        if (dados.getProjeto() != null) {
            tarefa.setProjeto(
                    projetoService.buscarPorId(
                            dados.getProjeto().getId()
                    )
            );
        }

        if (dados.getResponsavel() != null) {
            tarefa.setResponsavel(
                    responsavelService.buscarPorId(
                            dados.getResponsavel().getId()
                    )
            );
        } else {
            tarefa.setResponsavel(null);
        }

        if (dados.getStatus() == Status.CONCLUIDA) {
            tarefa.setConcluidaEm(LocalDateTime.now());
        }

        return tarefaRepository.save(tarefa);
    }

    public void remover(Long id) {

        Tarefa tarefa = buscarPorId(id);

        tarefaRepository.delete(tarefa);
    }
}
