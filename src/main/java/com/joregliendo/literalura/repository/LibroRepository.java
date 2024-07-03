package com.joregliendo.literalura.repository;

import com.joregliendo.literalura.model.libro.Idiomas;
import com.joregliendo.literalura.model.libro.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);
@Query("SELECT l FROM Libro l")
    List<Libro> listarLibrosGuardados();
@Query("SELECT l FROM Libro l WHERE l.autor.id=:id")
    List<Libro>buscarLibroPorAutor(Long id);
@Query("SELECT l FROM Libro l WHERE l.idioma=:idioma")
    List<Libro> buscarLibrosPorIdioma(@Param("idioma")Idiomas idioma);

}
