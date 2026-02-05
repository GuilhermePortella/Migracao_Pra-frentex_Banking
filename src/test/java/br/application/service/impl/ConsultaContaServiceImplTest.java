package br.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.domain.model.usuarios.Usuario;
import br.domain.repository.UsuarioRepositoryPort;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultaContaServiceImplTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @InjectMocks
    private ConsultaContaServiceImpl service;

    @Test
    void deveRetornarTrueQuandoContaExiste() {
        Usuario usuario = new Usuario(1L, "Ana", "Souza", LocalDate.of(1990, 1, 1),
                "ana@teste.com", "98765432100", "hash", "0001", "conta-001");
        when(usuarioRepository.findByAgenciaAndConta("0001", "conta-001")).thenReturn(Optional.of(usuario));

        boolean existe = service.contaExiste("0001", "conta-001");

        assertTrue(existe);
        verify(usuarioRepository).findByAgenciaAndConta("0001", "conta-001");
    }

    @Test
    void deveRetornarFalseQuandoContaNaoExiste() {
        when(usuarioRepository.findByAgenciaAndConta("0001", "conta-999")).thenReturn(Optional.empty());

        boolean existe = service.contaExiste("0001", "conta-999");

        assertFalse(existe);
        verify(usuarioRepository).findByAgenciaAndConta("0001", "conta-999");
    }
}
