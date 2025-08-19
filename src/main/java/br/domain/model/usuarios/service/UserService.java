package br.domain.model.usuarios.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    /**
         definição em SecurityConfig.
     */
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String codificarSenha(String senhaPura) {
        // Usa o encoder para criar um hash seguro da senha
        // O BCrypt já gera e embute um "salt" aleatório no hash
        return passwordEncoder.encode(senhaPura);
    }

    public boolean verificarSenha(String senhaPura, String senhaCodificada) {
        // Usa o encoder para comparar a senha pura com a versão codificada
        return passwordEncoder.matches(senhaPura, senhaCodificada);
    }
}


