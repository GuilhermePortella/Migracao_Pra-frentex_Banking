package br.domain.repository;

import br.domain.model.usuarios.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private static final RowMapper<Usuario> USUARIO_ROW_MAPPER = (rs, rowNum) -> {
        Date dataNascimento = rs.getDate("data_nascimento");
        LocalDate data = dataNascimento != null ? dataNascimento.toLocalDate() : null;

        return new Usuario(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("sobrenome"),
                data,
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("senha_hash"),
                rs.getString("agencia"),
                rs.getString("conta"));
    };

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            return insert(usuario);
        }

        update(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> findByCpf(String cpf) {
        String sql = """
                SELECT id, nome, sobrenome, data_nascimento, email, cpf, senha_hash, agencia, conta
                FROM usuarios
                WHERE cpf = ?
                """;
        return findOne(sql, cpf);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = """
                SELECT id, nome, sobrenome, data_nascimento, email, cpf, senha_hash, agencia, conta
                FROM usuarios
                WHERE email = ?
                """;
        return findOne(sql, email);
    }

    @Override
    public Optional<Usuario> findByAgenciaAndConta(String agencia, String conta) {
        String sql = """
                SELECT id, nome, sobrenome, data_nascimento, email, cpf, senha_hash, agencia, conta
                FROM usuarios
                WHERE agencia = ? AND conta = ?
                """;
        return findOne(sql, agencia, conta);
    }

    private Usuario insert(Usuario usuario) {
        String sql = """
                INSERT INTO usuarios (nome, sobrenome, data_nascimento, email, cpf, senha_hash, agencia, conta)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getCpf());
            ps.setString(6, usuario.getSenhaHash());
            ps.setString(7, usuario.getAgencia());
            ps.setString(8, usuario.getConta());
            return ps;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId == null) {
            throw new IllegalStateException("Falha ao gerar id do usuario.");
        }

        usuario.setId(generatedId.longValue());
        return usuario;
    }

    private void update(Usuario usuario) {
        String sql = """
                UPDATE usuarios
                SET nome = ?, sobrenome = ?, data_nascimento = ?, email = ?, cpf = ?, senha_hash = ?, agencia = ?, conta = ?
                WHERE id = ?
                """;

        int updatedRows = jdbcTemplate.update(sql,
                usuario.getNome(),
                usuario.getSobrenome(),
                Date.valueOf(usuario.getDataNascimento()),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenhaHash(),
                usuario.getAgencia(),
                usuario.getConta(),
                usuario.getId());

        if (updatedRows == 0) {
            throw new IllegalStateException("Usuario com id %d nao encontrado para atualizacao."
                    .formatted(usuario.getId()));
        }
    }

    private Optional<Usuario> findOne(String sql, Object... params) {
        List<Usuario> usuarios = jdbcTemplate.query(sql, USUARIO_ROW_MAPPER, params);
        return usuarios.stream().findFirst();
    }

}
