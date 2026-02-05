package br.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.domain.model.usuarios.Usuario;
import br.domain.repository.UsuarioRepositoryPort;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class CadastroUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CadastroUsuarioService service;

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        Usuario usuario = novoUsuario();
        when(usuarioRepository.findByCpf(usuario.getCpf())).thenReturn(Optional.of(usuario));

        assertThrows(IllegalStateException.class, () -> service.execute(usuario, "senha123"));

        verify(usuarioRepository).findByCpf(usuario.getCpf());
        verify(usuarioRepository, never()).findByEmail(any());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        Usuario usuario = novoUsuario();
        when(usuarioRepository.findByCpf(usuario.getCpf())).thenReturn(Optional.empty());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        assertThrows(IllegalStateException.class, () -> service.execute(usuario, "senha123"));

        verify(usuarioRepository).findByCpf(usuario.getCpf());
        verify(usuarioRepository).findByEmail(usuario.getEmail());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveGerarContaCodificarSenhaESalvarUsuario() {
        Usuario usuario = novoUsuario();
        when(usuarioRepository.findByCpf(usuario.getCpf())).thenReturn(Optional.empty());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha-segura")).thenReturn("senha-hash");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario salvo = service.execute(usuario, "senha-segura");

        assertSame(usuario, salvo);
        assertEquals("senha-hash", usuario.getSenhaHash());
        assertNotNull(usuario.getAgencia());
        assertTrue(usuario.getAgencia().matches("\\d{4}"));
        assertEquals(4, usuario.getAgencia().length());
        assertNotNull(usuario.getConta());
        assertEquals(36, usuario.getConta().length());
        assertNotNull(UUID.fromString(usuario.getConta()));

        verify(passwordEncoder).encode("senha-segura");
        verify(usuarioRepository).save(eq(usuario));
    }

    private Usuario novoUsuario() {
        return new Usuario(
                null,
                "Maria",
                "Silva",
                LocalDate.of(1995, 2, 10),
                "maria@teste.com",
                "12345678901",
                null,
                null,
                null);
    }
}
