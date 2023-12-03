package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "libros")
@Entity(name = "Libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "libroId")
public class Libro {
    @Id
    @Column(name = "libro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libroId;
    private String titulo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(nullable = false)
    @Lob
    private String portada;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id_fk")
    private Autor autor;

}
