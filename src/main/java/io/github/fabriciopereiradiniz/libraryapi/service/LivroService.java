package io.github.fabriciopereiradiniz.libraryapi.service;

import io.github.fabriciopereiradiniz.libraryapi.model.GeneroLivro;
import io.github.fabriciopereiradiniz.libraryapi.model.Livro;
import io.github.fabriciopereiradiniz.libraryapi.repository.LivroRepository;
import io.github.fabriciopereiradiniz.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisa(String titulo, String isbn, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){

        Specification<Livro> specs = Specification.where((root,query,cb)-> cb.conjunction());
        if(isbn != null){
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }

        if(titulo!=null){
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }

        if(genero!=null){
            specs = specs.and(LivroSpecs.generoEqual(genero));
        }

        return livroRepository.findAll(specs);
    }
}
