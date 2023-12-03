package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.service.LibroService;
import com.upao.biblioteca.infra.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para LibroService.
 * Utiliza Mockito para simular interacciones con el repositorio de libros.
 */

class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    /**
     * Prueba el método agregarLibro para asegurar que retorne el libro guardado.
     */

    @Test
    void cuandoSeAgregaLibro_retornarLibroGuardado() {
        // Configuración
        Libro libro = new Libro();
        libro.setTitulo("Nuevo Libro");
        libro.setAutor(new Autor());
        when(libroRepository.findByTituloAndAutorNombre(libro.getTitulo(), libro.getAutor().getNombre())).thenReturn(Optional.empty());
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);

        // Ejecución
        Libro result = libroService.agregarLibro(libro);

        // Verificación
        assertNotNull(result);
        assertEquals("Nuevo Libro", result.getTitulo());
    }

    /**
     * Prueba el método obtenerLibros para verificar que se devuelven libros disponibles.
     */

    @Test
    void cuandoHayLibros_disponible() {
        // Configuración
        Pageable pageable = mock(Pageable.class);
        Page<Libro> pageMock = mock(Page.class);
        when(pageMock.isEmpty()).thenReturn(false);
        when(libroRepository.findAllByOrderByTituloAsc(pageable)).thenReturn(pageMock);

        // Ejecución
        Page<Libro> result = libroService.obtenerLibros(pageable);

        // Verificación
        assertNotNull(result);
        verify(libroRepository).findAllByOrderByTituloAsc(pageable);
    }

    /**
     * Prueba el método agregarLibro para lanzar una excepción si el libro ya existe.
     */

    @Test
    void cuandoLibroExiste_lanzarExcepcion() {
        // Configuración
        Libro libro = new Libro();
        libro.setTitulo("Libro Existente");
        libro.setAutor(new Autor());
        when(libroRepository.findByTituloAndAutorNombre(libro.getTitulo(), libro.getAutor().getNombre())).thenReturn(Optional.of(libro));

        // Ejecución y Verificación
        assertThrows(ResponseStatusException.class, () -> libroService.agregarLibro(libro));
    }

    /**
     * Prueba el método obtenerLibros para lanzar una excepción si no hay libros disponibles.
     */

    @Test
    void cuandoNoHayLibros_lanzarExcepcion() {
        // Configuración
        Pageable pageable = mock(Pageable.class);
        Page<Libro> pageMock = mock(Page.class);
        when(pageMock.isEmpty()).thenReturn(true);
        when(pageMock.isFirst()).thenReturn(true);
        when(libroRepository.findAllByOrderByTituloAsc(pageable)).thenReturn(pageMock);

        // Ejecución y Verificación
        assertThrows(ResponseStatusException.class, () -> libroService.obtenerLibros(pageable));
    }
}
