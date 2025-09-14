package br.domain.model.usuarios;

import java.time.LocalDate;

public record UsuarioPj(Long id, String razaoSocial, String nomeFantasia, LocalDate dataDeAbertura, String email,
      String cnpj, String senhaHash, String agencia, String conta) {

   public void atribuirNovaConta(String agencia, String conta) {
      if (agencia == null || agencia.isBlank() || conta == null || conta.isBlank()) {
         throw new IllegalArgumentException("Agência e conta não podem ser nulos ou vazios.");
      }
   }
}
