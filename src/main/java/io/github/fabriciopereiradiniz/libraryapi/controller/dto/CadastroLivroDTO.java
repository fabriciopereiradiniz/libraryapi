package io.github.fabriciopereiradiniz.libraryapi.controller.dto;

import io.github.fabriciopereiradiniz.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(@ISBN @NotBlank(message = "campo obrigatorio") String isbn, @NotBlank(message = "campo obrigatorio") String titulo, @NotNull(message = "campo obrigatorio") @Past(message = "nao pode ser uma data futura") LocalDate dataPublicacao, GeneroLivro genero, @NotNull(message = "campo obrigatorio") UUID idAutor) {
}
