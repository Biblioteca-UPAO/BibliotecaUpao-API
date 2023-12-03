package com.upao.biblioteca.controller;

import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.entity.Solicitud;
import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.infra.repository.LibroRepository;
import com.upao.biblioteca.infra.repository.SolicitudRepository;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @PostMapping
    public Solicitud realizarSolicitud(@RequestParam Long usuarioId, @RequestParam Long libroId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Libro libro = libroRepository.findById(libroId).orElseThrow();

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario);
        solicitud.setLibro(libro);

        return  solicitudRepository.save(solicitud);
    }

    @GetMapping
    public List<Solicitud> verSolicitudes(){
        return solicitudRepository.findAll();
    }


}
