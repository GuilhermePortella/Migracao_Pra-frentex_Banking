package br.application.service.impl;

import br.application.service.ConsultaContaService;
import br.domain.repository.UsuarioRepositoryPort;

import org.springframework.stereotype.Service;

@Service
public class ConsultaContaServiceImpl implements ConsultaContaService {

    private final UsuarioRepositoryPort usuarioRepository;

    public ConsultaContaServiceImpl(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean contaExiste(String agencia, String conta) {
        return usuarioRepository.findByAgenciaAndConta(agencia, conta).isPresent();
    }
}
