package com.upao.biblioteca.domain.dto.libroDto;

import com.upao.biblioteca.domain.entity.Estado;
import com.upao.biblioteca.domain.entity.Libro;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Libro}
 */
public record DatosListadoLibro(String titulo, Estado estado, String portada, Long autorAutorId,
                                String autorNombre) implements Serializable {
    public DatosListadoLibro(Libro libro){
        this(libro.getTitulo(), libro.getEstado(), libro.getPortada(), libro.getAutor().getAutorId(), libro.getAutor().getNombre());
    }
}