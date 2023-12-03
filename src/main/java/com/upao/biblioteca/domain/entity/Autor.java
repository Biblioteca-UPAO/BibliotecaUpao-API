package com.upao.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "autorId")
public class Autor {
    @Id
    @Column(name = "autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    private String nombre;
    private String nacionalidad;
    @Column(length = 1000)
    private String biografia;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libro;
}
