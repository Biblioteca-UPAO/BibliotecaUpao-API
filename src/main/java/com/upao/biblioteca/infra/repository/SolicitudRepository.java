package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
