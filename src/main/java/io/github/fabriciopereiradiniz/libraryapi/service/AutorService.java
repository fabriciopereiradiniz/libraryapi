package io.github.fabriciopereiradiniz.libraryapi.service;

import io.github.fabriciopereiradiniz.libraryapi.exceptions.OperacaoNaoPermitida;
import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import io.github.fabriciopereiradiniz.libraryapi.repository.AutorRepository;
import io.github.fabriciopereiradiniz.libraryapi.repository.LivroRepository;
import io.github.fabriciopereiradiniz.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar o autor precisa estar na base");
        }
        validator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitida("Nao eh permitido excluir um autor que possui livros cadastrados");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if (nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null){
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor, matcher);
        return autorRepository.findAll(autorExample);
    }
    public boolean possuiLivro(Autor autor){
    return livroRepository.existsByAutor(autor);
    }

}
