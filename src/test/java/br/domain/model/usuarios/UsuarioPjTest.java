package br.domain.model.usuarios;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class UsuarioPjTest {

    @Test
    void testAtribuirNovaContaComSucesso() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(), "email@empresa.com", "12345678000190", "senha", null, null);
        usuarioPj.atribuirNovaConta("0002", "54321-0");
        assertEquals("0002", usuarioPj.getAgencia());
        assertEquals("54321-0", usuarioPj.getConta());
    }

    @Test
    void testAtribuirNovaContaComAgenciaNula() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(), "email@empresa.com", "12345678000190", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioPj.atribuirNovaConta(null, "54321-0");
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testAtribuirNovaContaComContaNula() {
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social", "Nome Fantasia", LocalDate.now(), "email@empresa.com", "12345678000190", "senha", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioPj.atribuirNovaConta("0002", null);
        });
        assertEquals("Agência e conta não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testConstructorAndGetters() {
        LocalDate dataAbertura = LocalDate.of(2010, 5, 15);
        UsuarioPj usuarioPj = new UsuarioPj(1L, "Razao Social Teste", "Nome Fantasia Teste", dataAbertura, "contato@empresa.com", "98765432000180", "outrasenha", "0003", "98765-4");

        assertEquals(1L, usuarioPj.getId());
        assertEquals("Razao Social Teste", usuarioPj.getRazaoSocial());
        assertEquals("Nome Fantasia Teste", usuarioPj.getNomeFantasia());
        assertEquals(dataAbertura, usuarioPj.getDataDeAbertura());
        assertEquals("contato@empresa.com", usuarioPj.getEmail());
        assertEquals("98765432000180", usuarioPj.getCnpj());
        assertEquals("outrasenha", usuarioPj.getSenhaHash());
        assertEquals("0003", usuarioPj.getAgencia());
        assertEquals("98765-4", usuarioPj.getConta());
    }
}
