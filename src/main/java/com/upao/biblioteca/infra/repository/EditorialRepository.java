package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}
