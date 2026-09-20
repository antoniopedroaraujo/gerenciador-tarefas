package com.example.gerenciador_tarefas.tarefa;

import com.example.gerenciador_tarefas.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefasService tarefasService;

    public TarefaController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa tarefaCriada = tarefasService.criar(tarefa);

        URI location = URI.create("/tarefas/" + tarefaCriada.getId());

        return ResponseEntity.created(location).body(tarefaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long projetoId
    ) {
        return ResponseEntity.ok(tarefasService.listar(status, projetoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefasService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(
            @PathVariable Long id,
            @RequestBody Tarefa dados) {
        return ResponseEntity.ok(tarefasService.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tarefasService.remover(id);

        return ResponseEntity.noContent().build();
    }
}
