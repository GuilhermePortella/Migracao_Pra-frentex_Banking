package br.domain.model.usuarios;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class UsuarioTest {

    @Test
    void testAtribuirNovaContaComSucesso() {
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", LocalDate.now(), "email@test.com", "12345678901", "senha", null, null);
        usuario.atribuirNovaConta("0001", "12345-6");
        assertEquals("0001", usuario.getAgencia());
        assertEquals("12345-6", usuario.getConta());
    }

    @Test
    void testAtribuirNovaContaComAgenciaNula() {
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", LocalDate.now(), "email@test.com", "12345678901", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuario.atribuirNovaConta(null, "12345-6");
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testAtribuirNovaContaComContaNula() {
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", LocalDate.now(), "email@test.com", "12345678901", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuario.atribuirNovaConta("0001", null);
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testAtribuirNovaContaComAgenciaVazia() {
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", LocalDate.now(), "email@test.com", "12345678901", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuario.atribuirNovaConta("", "12345-6");
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testAtribuirNovaContaComContaVazia() {
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", LocalDate.now(), "email@test.com", "12345678901", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuario.atribuirNovaConta("0001", "");
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testConstructorAndGetters() {
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        Usuario usuario = new Usuario(1L, "Nome", "Sobrenome", dataNascimento, "email@test.com", "12345678901", "senha", "0001", "12345-6");

        assertEquals(1L, usuario.getId());
        assertEquals("Nome", usuario.getNome());
        assertEquals("Sobrenome", usuario.getSobrenome());
        assertEquals(dataNascimento, usuario.getDataNascimento());
        assertEquals("email@test.com", usuario.getEmail());
        assertEquals("12345678901", usuario.getCpf());
        assertEquals("senha", usuario.getSenhaHash());
        assertEquals("0001", usuario.getAgencia());
        assertEquals("12345-6", usuario.getConta());
    }
}
