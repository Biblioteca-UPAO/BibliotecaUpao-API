package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "editoriales")
@Entity(name = "Editorial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "editorialId")
public class Editorial {
    @Id
    @Column(name = "editorial_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long editorialId;
    private String nombre;
    private String ciudad;
}
