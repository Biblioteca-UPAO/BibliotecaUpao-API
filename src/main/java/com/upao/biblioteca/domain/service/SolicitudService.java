package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.entity.Solicitud;
import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.infra.repository.LibroRepository;
import com.upao.biblioteca.infra.repository.SolicitudRepository;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    public Solicitud realizarSolicitud(Long usuarioId, Long libroId) {

        validarUsuarioExistente(usuarioId);
        validarLibroExistente(libroId);

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Libro libro = libroRepository.findById(libroId).orElseThrow();

        validarSolicitudDuplicada(usuario, libro);

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario);
        solicitud.setLibro(libro);

        return solicitudRepository.save(solicitud);
    }

    public List<Solicitud> verSolicitudes() {
        return solicitudRepository.findAll();
    }
    

    private void validarUsuarioExistente(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    private void validarLibroExistente(Long libroId) {
        if (!libroRepository.existsById(libroId)) {
            throw new IllegalArgumentException("Libro no encontrado con ID: " + libroId);
        }
    }

    private void validarSolicitudDuplicada(Usuario usuario, Libro libro) {
        // Puedes implementar lógica para evitar solicitudes duplicadas
        if (solicitudRepository.existsByUsuarioAndLibro(usuario, libro)) {
            throw new IllegalStateException("Ya existe una solicitud para este usuario y libro.");
        }
    }
}





