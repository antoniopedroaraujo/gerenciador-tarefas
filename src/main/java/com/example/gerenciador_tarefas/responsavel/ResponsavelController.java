package com.example.gerenciador_tarefas.responsavel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    public ResponseEntity<Responsavel> criar(@RequestBody Responsavel responsavel ) {
        Responsavel responsavelCriado = responsavelService.criar(responsavel);
        return ResponseEntity .status(HttpStatus.CREATED) .body(responsavelCriado);
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.ok(responsavelService.listar());
    }

    @GetMapping("/{id}") public ResponseEntity<Responsavel> buscarPorId( @PathVariable Long id ) {
        return ResponseEntity.ok( responsavelService.buscarPorId(id) );
    }
}
