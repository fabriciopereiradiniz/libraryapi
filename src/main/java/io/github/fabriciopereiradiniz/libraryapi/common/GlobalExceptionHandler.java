package io.github.fabriciopereiradiniz.libraryapi.common;

import io.github.fabriciopereiradiniz.libraryapi.controller.dto.ErroCampo;
import io.github.fabriciopereiradiniz.libraryapi.controller.dto.ErroResposta;
import io.github.fabriciopereiradiniz.libraryapi.exceptions.OperacaoNaoPermitida;
import io.github.fabriciopereiradiniz.libraryapi.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaDeErros = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta((HttpStatus.UNPROCESSABLE_ENTITY.value()),"Erro de validacao",listaDeErros);
    }
    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitida.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitida e) {
        return ErroResposta.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratados(RuntimeException e){
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado.", List.of());
    }

}
