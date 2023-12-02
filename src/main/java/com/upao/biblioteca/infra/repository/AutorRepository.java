package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
