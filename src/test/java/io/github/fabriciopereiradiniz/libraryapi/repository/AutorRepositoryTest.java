package io.github.fabriciopereiradiniz.libraryapi.repository;

import io.github.fabriciopereiradiniz.libraryapi.model.Autor;
import io.github.fabriciopereiradiniz.libraryapi.model.GeneroLivro;
import io.github.fabriciopereiradiniz.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;
    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Fabricio");
        autor.setNacionalidade("brasileiro");
        autor.setDataNascimento(LocalDate.of(1950,1,2));
        var autorSalvo = repository.save(autor);
        System.out.println(autorSalvo);
    }


    @Test
    public void atualizarTest(){
        var id = UUID.fromString("");
        Optional<Autor> possibleAutor = repository.findById(id);
        if(possibleAutor.isPresent()){
            Autor autorEncontrado = possibleAutor.get();
            System.out.println(autorEncontrado);
            autorEncontrado.setNome("Outro nome da silva");
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println(repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id=  UUID.fromString("");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id=  UUID.fromString("");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Fabricio");
        autor.setNacionalidade("brasileiro");
        autor.setDataNascimento(LocalDate.of(1950,1,2));

        Livro livro = new Livro();
        livro.setIsbn("");
        livro.setPreco(0.0);
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Fabricio");
        livro.setData_publicacao(LocalDate.of(1950,1,2));

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
        livroRepository.save(livro);
    }
}
