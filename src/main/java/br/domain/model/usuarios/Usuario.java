package br.domain.model.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Usuario {

   private Long id;
   private String nome;
   private String sobrenome;
   private LocalDate dataNascimento;
   private String email;
   private String cpf;
   private String senhaHash;
   private String agencia;
   private String conta;

   public void atribuirNovaConta(String agencia, String conta) {
      if (agencia == null || agencia.isBlank() || conta == null || conta.isBlank()) {
         throw new IllegalArgumentException("Agência e conta não podem ser nulos ou vazios.");
      }
      this.agencia = agencia;
      this.conta = conta;
   }
}
