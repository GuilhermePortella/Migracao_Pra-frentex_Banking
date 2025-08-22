package br.infrastructure.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.application.usecase.CadastrarUsuarioUseCase;
import br.domain.model.usuarios.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Adaptador de Entrada (Driving Adapter) para interagir com a aplicação
 * via linha de comando (CLI). Substitui a lógica do antigo ServiceProcess.
 */
@Profile("!test")
@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApplicationRunner(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- BEM-VINDO AO PRA-FRENTEX BANKING (CONSOLE) ---");
        System.out.println("O que você gostaria de fazer?");
        System.out.println("1. Abrir nova conta");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opcao: ");

        String opcao = scanner.nextLine();

        if ("1".equals(opcao)) {
            abrirNovaConta();
        }

        System.out.println("\n--- APLICAÇÃO FINALIZADA ---");
    }

    private void abrirNovaConta() {
        try {
            System.out.println("\n--- ABERTURA DE CONTA (PESSOA FÍSICA) ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Sobrenome: ");
            String sobrenome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.print("Crie uma senha (mínimo 6 caracteres): ");
            String senha = scanner.nextLine();

            Usuario novoUsuario = new Usuario(null, nome, sobrenome, dataNascimento, email, cpf, null, null, null);
            Usuario usuarioSalvo = cadastrarUsuarioUseCase.execute(novoUsuario, senha);

            System.out.println("\n--- CONTA CRIADA COM SUCESSO! ---");
            System.out.printf("Titular: %s %s%nAgência: %s%nConta: %s%n", usuarioSalvo.getNome(),
                    usuarioSalvo.getSobrenome(), usuarioSalvo.getAgencia(), usuarioSalvo.getConta());
        } catch (Exception e) {
            System.err.println("\nERRO AO CRIAR CONTA: " + e.getMessage());
        }
    }
}