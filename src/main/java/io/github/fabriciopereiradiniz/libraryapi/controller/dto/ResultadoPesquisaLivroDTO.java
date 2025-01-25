package io.github.fabriciopereiradiniz.libraryapi.controller.dto;

import io.github.fabriciopereiradiniz.libraryapi.model.GeneroLivro;

import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(UUID id, String isbn, String titulo, LocalDate dataPublicacao, GeneroLivro genero, AutorDTO autor) {
}
