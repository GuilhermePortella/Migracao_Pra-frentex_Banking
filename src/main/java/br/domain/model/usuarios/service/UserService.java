package br.domain.model.usuarios.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String codificarSenha(String senhaPura) {
        return passwordEncoder.encode(senhaPura);
    }

    public boolean verificarSenha(String senhaPura, String senhaCodificada) {
        return passwordEncoder.matches(senhaPura, senhaCodificada);
    }
}


