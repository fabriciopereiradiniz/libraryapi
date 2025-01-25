package io.github.fabriciopereiradiniz.libraryapi.service;

import io.github.fabriciopereiradiniz.libraryapi.model.Livro;
import io.github.fabriciopereiradiniz.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }
}
