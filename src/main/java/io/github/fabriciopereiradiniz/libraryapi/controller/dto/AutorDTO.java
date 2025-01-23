package io.github.fabriciopereiradiniz.libraryapi.controller.dto;

import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

// data transfer object
public record AutorDTO(UUID id, @NotBlank(message = "campo obrigatorio") @Size(max = 100, message = "campo fora do tamanho padrao") String nome, @NotNull(message = "campo obrigatorio") LocalDate dataNascimento, @NotBlank(message = "campo obrigatorio") @Size(max = 100, min = 2, message = "campo fora do tamanho padrao")String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
