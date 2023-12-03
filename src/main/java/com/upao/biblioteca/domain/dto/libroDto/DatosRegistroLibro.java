package com.upao.biblioteca.domain.dto.libroDto;

import com.upao.biblioteca.domain.entity.Estado;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Libro}
 */
public record DatosRegistroLibro(String titulo, Estado estado, String portada,
                                 String autorNombre) implements Serializable {
}