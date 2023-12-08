package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.service.AutorService;
import com.upao.biblioteca.infra.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    public void setUp() {
        autorService = new AutorService(autorRepository);
    }

    @Test
    public void testBuscarPorNombre() {
        String nombreBuscado = "Gabriel Garcia Marquez";
        Autor autorEsperado = new Autor(nombreBuscado);
        when(autorRepository.findByNombre(nombreBuscado)).thenReturn(Optional.of(autorEsperado));

        Optional<Autor> resultado = autorService.buscarPorNombre(nombreBuscado);

        assertEquals(Optional.of(autorEsperado), resultado, "El autor retornado debe coincidir con el esperado");
    }

    @Test
    //buscarPorNombre
    public void cuandoBuscaPorNombreExistente_retornaAutor() {

        Autor autor = new Autor();
        autor.setAutorId(1L);
        autor.setNombre("Nombre Autor");

        when(autorRepository.findByNombre("Nombre Autor")).thenReturn(Optional.of(autor));

        Optional<Autor> resultado = autorService.buscarPorNombre("Nombre Autor");

        assertTrue(resultado.isPresent());
        assertEquals("Nombre Autor", resultado.get().getNombre());

    }

    @Test
    public void cuandoBuscaPorNombreInexistente_retornaVacio() {
        when(autorRepository.findByNombre("Nombre Inexistente")).thenReturn(Optional.empty());

        Optional<Autor> resultado = autorService.buscarPorNombre("Nombre Inexistente");

        assertFalse(resultado.isPresent());
    }

}