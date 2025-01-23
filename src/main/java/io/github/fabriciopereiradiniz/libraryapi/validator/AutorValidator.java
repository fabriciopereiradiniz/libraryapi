package io.github.fabriciopereiradiniz.libraryapi.validator;

import io.github.fabriciopereiradiniz.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import io.github.fabriciopereiradiniz.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AutorValidator {
    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor){
        if(existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor ja cadastrado");
        }
    }

    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),autor.getDataNascimento(),autor.getNacionalidade());

        if(autor.getId()==null){
            return autorEncontrado.isPresent();
        }

        return autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();


    }
}
