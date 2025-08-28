package br.domain.model.usuarios.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCodificarSenha() {
        String senhaPura = "senha123";
        String senhaCodificada = "senhaCodificada123";

        when(passwordEncoder.encode(senhaPura)).thenReturn(senhaCodificada);

        String resultado = userService.codificarSenha(senhaPura);

        assertEquals(senhaCodificada, resultado);
        verify(passwordEncoder).encode(senhaPura);
    }

    @Test
    public void testVerificarSenha() {
        String senhaPura = "senha123";
        String senhaCodificada = "senhaCodificada123";

        when(passwordEncoder.matches(senhaPura, senhaCodificada)).thenReturn(true);

        boolean resultado = userService.verificarSenha(senhaPura, senhaCodificada);

        assertTrue(resultado);
        verify(passwordEncoder).matches(senhaPura, senhaCodificada);
    }
}
