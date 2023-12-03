package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.infra.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LibroService {

    @Autowired
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Page<Libro> obtenerLibros(Pageable pageable) {
        Page<Libro> libros = libroRepository.findAllByOrderByTituloAsc(pageable);

        if (libros.isEmpty() && libros.isFirst()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libros no encontrados");
        }

        return libros;
    }

    public Libro agregarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getAutor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y autor son obligatorios");
        }

        libroRepository.findByTituloAndAutorNombre(libro.getTitulo(), libro.getAutor().getNombre())
                .ifPresent(existingLibro -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un libro con el mismo título y autor");
                });
        return libroRepository.save(libro);
    }
}
