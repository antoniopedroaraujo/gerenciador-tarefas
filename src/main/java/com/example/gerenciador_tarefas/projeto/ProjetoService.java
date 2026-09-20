package com.example.gerenciador_tarefas.projeto;

import com.example.gerenciador_tarefas.exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Projeto não encontrado: " + id
                        )
                );
    }
}
