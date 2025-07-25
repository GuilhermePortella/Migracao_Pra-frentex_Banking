package br.application.usecase;

import br.domain.model.usuarios.Usuario;

public interface CadastrarUsuarioUseCase {

    Usuario execute(Usuario usuario, String senha);

}
