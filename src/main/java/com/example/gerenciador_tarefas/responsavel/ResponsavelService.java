package com.example.gerenciador_tarefas.responsavel;

import com.example.gerenciador_tarefas.exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public Responsavel criar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> listar() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(Long id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Responsável não encontrado: " + id
                        )
                );
    }
}
