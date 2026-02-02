package br.domain.repository;

import br.domain.model.usuarios.Usuario;
import java.util.Optional;


/**
 *
 * @author Guilherme
 */
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    @Override
    public Usuario save(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Usuario> findByCpf(String cpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Usuario> findByAgenciaAndConta(String agencia, String conta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
