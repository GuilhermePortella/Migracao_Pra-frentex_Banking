package br.application.service.impl;

import br.application.service.ConsultaContaService;
import br.domain.model.usuarios.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaContaServiceImpl implements ConsultaContaService {

    private final List<Usuario> usuariosEmMemoria = new ArrayList<>();

    public ConsultaContaServiceImpl() {
        Usuario usuarioTeste = new Usuario(
                1L,
                "Cliente",
                "Teste",
                LocalDate.of(1990, 1, 15),
                "teste@prafrentex.com",
                "12345678900",
                "0001", // Agência
                "12345-6", // Conta
                null
        );
        this.usuariosEmMemoria.add(usuarioTeste);
    }
    
    private Optional<Usuario> findByAgenciaAndConta(String agencia, String conta) {
        return usuariosEmMemoria.stream()
                .filter(u -> u.getAgencia().equals(agencia) && u.getConta().equals(conta))
                .findFirst();
    }

    @Override
    public boolean contaExiste(String agencia, String conta) {
        return findByAgenciaAndConta(agencia, conta).isPresent();
    }
}
