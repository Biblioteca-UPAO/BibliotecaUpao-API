package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa un autor en el contexto de una biblioteca.
 * Cada autor tiene un identificador único, nombre, nacionalidad, biografía y fecha de nacimiento.
 * Además, un autor puede estar asociado con varios libros.
 *
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "autorId")
public class Autor {
    /**
     * Identificador único del autor.
     */
    @Id
    @Column(name = "autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    /**
     * Nombre del autor.
     */
    private String nombre;
    /**
     * Nacionalidad del autor.
     */
    private String nacionalidad;
    @Column(length = 1000)
    /**
     * Biografía del autor, con una longitud máxima de 1000 caracteres.
     */
    private String biografia;
    /**
     * Fecha de nacimiento del autor.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    /**
     * Lista de libros asociados con el autor.
     */
    @OneToMany(mappedBy = "autor")
    private List<Libro> libro;
}
