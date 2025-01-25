package io.github.fabriciopereiradiniz.libraryapi.mappers;
import io.github.fabriciopereiradiniz.libraryapi.controller.dto.AutorDTO;
import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);
    AutorDTO toDto(Autor entity);
}
