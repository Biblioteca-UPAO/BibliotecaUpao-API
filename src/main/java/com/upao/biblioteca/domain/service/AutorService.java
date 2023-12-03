package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.infra.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Optional<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    public Autor guardarAutor(Autor autor) {
        try {
            return autorRepository.save(autor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el autor", e);
        }
    }
}
