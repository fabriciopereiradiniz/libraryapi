package io.github.fabriciopereiradiniz.libraryapi.repository;

import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
