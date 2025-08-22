package br.infrastructure.cli.commands;

import br.application.usecase.CadastrarUsuarioUseCase;
import br.domain.model.usuarios.Usuario;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Command(name = "abrir-conta", description = "Abre uma nova conta de pessoa física.")
public class AbrirContaCommand implements Runnable {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public AbrirContaCommand(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @Option(names = {"--nome"}, description = "Nome do titular", required = true)
    private String nome;

    @Option(names = {"--sobrenome"}, description = "Sobrenome do titular", required = true)
    private String sobrenome;

    @Option(names = {"--cpf"}, description = "CPF do titular", required = true)
    private String cpf;

    @Option(names = {"--email"}, description = "Email do titular", required = true)
    private String email;

    @Option(names = {"--data-nascimento"}, description = "Data de nascimento (dd/MM/yyyy)", required = true)
    private String dataNascimentoStr;

    @Option(names = {"--senha"}, description = "Senha para a conta (mínimo 6 caracteres)", required = true)
    private String senha;

    @Override
    public void run() {
        try {
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Usuario novoUsuario = new Usuario(null, nome, sobrenome, dataNascimento, email, cpf, null, null, null);
            Usuario usuarioSalvo = cadastrarUsuarioUseCase.execute(novoUsuario, senha);

            System.out.println("\n--- CONTA CRIADA COM SUCESSO! ---");
            System.out.printf("Titular: %s %s%nAgência: %s%nConta: %s%n",
                    usuarioSalvo.getNome(), usuarioSalvo.getSobrenome(),
                    usuarioSalvo.getAgencia(), usuarioSalvo.getConta());

        } catch (Exception e) {
            System.err.println("\nERRO AO CRIAR CONTA: " + e.getMessage());
        }
    }
}
