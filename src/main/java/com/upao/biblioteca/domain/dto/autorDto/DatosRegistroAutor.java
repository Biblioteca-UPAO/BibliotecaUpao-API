package com.upao.biblioteca.domain.dto.autorDto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Autor}
 */
public record DatosRegistroAutor(@NotNull String nombre, String nacionalidad, String biografia,
                                 String fechaNacimiento) implements Serializable {
}