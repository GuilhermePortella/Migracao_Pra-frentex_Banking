package br.domain.repository;

import java.util.Optional;

import br.domain.model.usuarios.Usuario;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);
}
