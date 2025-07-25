package br.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.application.usecase.CadastrarUsuarioUseCase;
import br.domain.model.usuarios.Usuario;
import br.domain.repository.UsuarioRepositoryPort;

import java.util.Random;

@Service
public class CadastroUsuarioService implements CadastrarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    public CadastroUsuarioService(UsuarioRepositoryPort usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario execute(Usuario usuario, String senha) {
        usuarioRepository.findByCpf(usuario.getCpf()).ifPresent(u -> {
            throw new IllegalStateException("CPF já cadastrado.");
        });
        usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(u -> {
            throw new IllegalStateException("Email já cadastrado.");
        });

        String agencia = String.format("%04d", random.nextInt(10000));
        String conta = String.format("%09d", random.nextInt(1000000000));
        usuario.atribuirNovaConta(agencia, conta);

        usuario.setSenhaHash(passwordEncoder.encode(senha));

        return usuarioRepository.save(usuario);
    }
}
