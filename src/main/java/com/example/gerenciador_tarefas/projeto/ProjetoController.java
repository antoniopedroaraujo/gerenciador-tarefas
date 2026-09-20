package com.example.gerenciador_tarefas.projeto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    private final ProjetoService projetoService;
    public ProjetoController(ProjetoService projetoService) { this.projetoService = projetoService; }

    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody Projeto projeto) {
        Projeto projetoCriado = projetoService.criar(projeto);
        return ResponseEntity .status(HttpStatus.CREATED) .body(projetoCriado);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listar() {
        return ResponseEntity.ok(projetoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }
}
