package br.domain.model.usuarios;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UsuarioPjTest {

    @Test
    void testAtribuirNovaContaComSucesso() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(),
                "email@empresa.com", "12345678000190", "senha", null, null);

        assertDoesNotThrow(() -> usuarioPj.atribuirNovaConta("0002", "54321-0"));
        assertNull(usuarioPj.agencia());
        assertNull(usuarioPj.conta());
    }

    @Test
    void testAtribuirNovaContaComAgenciaNula() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(),
                "email@empresa.com", "12345678000190", "senha", null, null);

        assertThrows(IllegalArgumentException.class, () -> usuarioPj.atribuirNovaConta(null, "54321-0"));
    }

    @Test
    void testAtribuirNovaContaComContaNula() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(),
                "email@empresa.com", "12345678000190", "senha", null, null);

        assertThrows(IllegalArgumentException.class, () -> usuarioPj.atribuirNovaConta("0002", null));
    }

    @Test
    void testConstructorAndGetters() {
        LocalDate dataAbertura = LocalDate.of(2010, 5, 15);
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social Teste", "Nome Fantasia Teste", dataAbertura,
                "contato@empresa.com", "98765432000180", "outrasenha", "0003", "98765-4");

        assertEquals(1L, usuarioPj.id());
        assertEquals("Razao Social Teste", usuarioPj.razaoSocial());
        assertEquals("Nome Fantasia Teste", usuarioPj.nomeFantasia());
        assertEquals(dataAbertura, usuarioPj.dataDeAbertura());
        assertEquals("contato@empresa.com", usuarioPj.email());
        assertEquals("98765432000180", usuarioPj.cnpj());
        assertEquals("outrasenha", usuarioPj.senhaHash());
        assertEquals("0003", usuarioPj.agencia());
        assertEquals("98765-4", usuarioPj.conta());
    }
}
