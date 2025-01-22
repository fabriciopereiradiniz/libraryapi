package io.github.fabriciopereiradiniz.libraryapi.repository;

import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import io.github.fabriciopereiradiniz.libraryapi.model.GeneroLivro;
import io.github.fabriciopereiradiniz.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("412341234");
        livro.setPreco(100);
        livro.setTitulo("titulo epico");
        livro.setData_publicacao(LocalDate.of(1980,1,2));
        livro.setGenero(GeneroLivro.FICCAO);
        Autor autor = autorRepository.findById(UUID.fromString("56f41e72-f55c-4ebd-87ae-be36acbf068f")).orElse(null);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("412341234");
        livro.setPreco(100);
        livro.setTitulo("titulo epico");
        livro.setData_publicacao(LocalDate.of(1980,1,2));
        livro.setGenero(GeneroLivro.FICCAO);

        Autor autor = new Autor();
        autor.setNome("joao");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1980,1,2));
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorLivroTest(){
        UUID id = UUID.fromString("");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("");
        var autorParaAtualizar = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autorParaAtualizar);
        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deleter(){
        UUID id = UUID.fromString("");
        livroRepository.deleteById(id);
    }

    @Test
    void buscarLivroTest(){
        UUID id = UUID.fromString("");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println(livro);
        System.out.println(livro.getAutor());
        System.out.println(livro.getTitulo());
        System.out.println("autor: " + livro.getAutor().getNome());
    }
}