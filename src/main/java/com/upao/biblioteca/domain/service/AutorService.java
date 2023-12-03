package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.infra.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Servicio que ofrece operaciones relacionadas con autores en la biblioteca.
 *
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 * @since 1.0
 */

@Service
public class AutorService {
    @Autowired
    private final AutorRepository autorRepository;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param autorRepository Repositorio para operaciones CRUD con autores.
     */
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    /**
     * Busca un autor por su nombre.
     *
     * @param nombre Nombre del autor a buscar.
     * @return Un Optional que contiene el autor si es encontrado.
     */
    public Optional<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    /**
     * Guarda o actualiza un autor en la base de datos.
     *
     * @param autor El autor a guardar o actualizar.
     * @return El autor guardado.
     * @throws ResponseStatusException Si ocurre un error al guardar el autor.
     */
    public Autor guardarAutor(Autor autor) {
        try {
            return autorRepository.save(autor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el autor", e);
        }
    }
}
