package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.entity.Editorial;
import com.upao.biblioteca.domain.entity.Estado;
import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.service.LibroService;
import com.upao.biblioteca.infra.repository.AutorRepository;
import com.upao.biblioteca.infra.repository.EditorialRepository;
import com.upao.biblioteca.infra.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para LibroService.
 * Utiliza Mockito para simular interacciones con el repositorio de libros.
 */

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setTitulo("Cien Años de Soledad");
        libro.setPortada("portada.jpg");
        libro.setEstado(Estado.DISPONIBLE);
        libro.setCodigoPublico("ABC123");

        Autor autor = new Autor("Gabriel García Márquez");
        Set<Autor> autores = new HashSet<>();
        autores.add(autor);
        libro.setAutores(autores);

        Editorial editorial = new Editorial();
        editorial.setNombre("Editorial XYZ");
        libro.setEditorial(editorial);
    }

    @Test
    void cuandoAgregarLibroConCodigoDuplicado_entoncesLanzaExcepcion() {
        when(libroRepository.findByCodigoPublico("ABC123")).thenReturn(Optional.of(libro));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            libroService.agregarLibro(libro);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(libroRepository, never()).save(libro);
    }
}
