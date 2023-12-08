package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.domain.service.UsuarioService;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import com.upao.biblioteca.infra.security.JwtService;
import com.upao.biblioteca.infra.security.LoginRequest;
import com.upao.biblioteca.infra.security.TokenResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para UsuarioService.
 * Utiliza Mockito para simular interacciones con el repositorio de usuarios y otros servicios.
 */

@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UsuarioService usuarioService;

    /**
     * Prueba el método de inicio de sesión para asegurar que retorne un token válido cuando las credenciales son correctas.
     */

    @Test
    public void cuandoUsuarioSeLoguea_retornarToken() {
        // Datos de prueba
        String username = "PablitoCrack";
        String password = "elpablito123";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);

        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));
        when(jwtService.getToken(any(UserDetails.class))).thenReturn("token_jwt_simulado");

        // Ejecución de la prueba
        TokenResponse tokenResponse = usuarioService.login(loginRequest);

        // Verificaciones
        assertNotNull(tokenResponse);
        assertEquals("token_jwt_simulado", tokenResponse.getToken());
    }

    /**
     * Prueba el método de agregar un nuevo usuario para asegurar que retorne un token válido.
     */

    @Test
    public void cuandoSeAgregaUnNuevoUsuario_retornarToken() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("NuevoUsuario");
        usuario.setPassword("contrasena123");
        usuario.setNombre("Juan Perez");
        usuario.setEdad("22");
        usuario.setTelefono("999888777");
        usuario.setCorreo("juanperez@correo.com");
        usuario.setDni("87654321");

        when(passwordEncoder.encode(usuario.getPassword())).thenReturn("contrasena_codificada");
        when(jwtService.getToken(any(UserDetails.class))).thenReturn("token_jwt_nuevo_usuario");

        // Ejecución de la prueba
        TokenResponse tokenResponse = usuarioService.addUsuario(usuario);

        // Verificaciones
        assertNotNull(tokenResponse);
        assertEquals("token_jwt_nuevo_usuario", tokenResponse.getToken());
    }

    /**
     * Prueba el método de inicio de sesión para lanzar una excepción cuando las credenciales son inválidas.
     */

    @Test
    public void cuandoCredencialesSonInvalidas_lanzarExcepcion() {
        // Datos de prueba
        String username = "UsuarioInexistente";
        String password = "contrasenaIncorrecta";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        // Configuración de Mockito para simular que el usuario no existe
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Ejecución de la prueba y verificación de la excepción esperada
        assertThrows(UsernameNotFoundException.class, () -> {
            usuarioService.login(loginRequest);
        });
    }
}