package br.domain.repository;

import br.domain.model.usuarios.Usuario;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(UsuarioRepositoryAdapter.class)
@ActiveProfiles("test")
@Sql("/db/migration/V1__create_usuarios_table.sql")
class UsuarioRepositoryAdapterTest {

    @Autowired
    private UsuarioRepositoryAdapter repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limparTabela() {
        jdbcTemplate.update("DELETE FROM usuarios");
    }

    @Test
    void deveSalvarEBuscarUsuarioPorCpfEmailEConta() {
        Usuario usuario = new Usuario(
                null,
                "Maria",
                "Silva",
                LocalDate.of(1992, 10, 4),
                "maria.silva@example.com",
                "12345678901",
                "$2a$10$abcdef",
                "0001",
                "conta-001");

        Usuario salvo = repository.save(usuario);
        assertNotNull(salvo.getId());

        Optional<Usuario> porCpf = repository.findByCpf("12345678901");
        Optional<Usuario> porEmail = repository.findByEmail("maria.silva@example.com");
        Optional<Usuario> porConta = repository.findByAgenciaAndConta("0001", "conta-001");

        assertTrue(porCpf.isPresent());
        assertTrue(porEmail.isPresent());
        assertTrue(porConta.isPresent());
        assertEquals("Maria", porCpf.get().getNome());
        assertEquals("0001", porConta.get().getAgencia());
    }

    @Test
    void deveAtualizarUsuarioExistente() {
        Usuario usuario = new Usuario(
                null,
                "Joao",
                "Pereira",
                LocalDate.of(1988, 7, 22),
                "joao.pereira@example.com",
                "10987654321",
                "$2a$10$hash1",
                "0002",
                "conta-002");

        Usuario salvo = repository.save(usuario);
        salvo.setSobrenome("Oliveira");
        salvo.setSenhaHash("$2a$10$hash2");

        repository.save(salvo);

        Usuario atualizado = repository.findByCpf("10987654321").orElseThrow();
        assertEquals("Oliveira", atualizado.getSobrenome());
        assertEquals("$2a$10$hash2", atualizado.getSenhaHash());
    }
}
