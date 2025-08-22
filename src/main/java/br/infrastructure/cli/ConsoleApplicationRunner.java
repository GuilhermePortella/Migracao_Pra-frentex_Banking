package br.infrastructure.cli;

import br.infrastructure.cli.commands.AbrirContaCommand;
import br.infrastructure.cli.commands.ConsultarContaCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Profile("!test")
@Component
@CommandLine.Command(name = "banking", mixinStandardHelpOptions = true, 
                   version = "Pra-frentex Banking CLI 1.0",
                   description = "CLI para gerenciamento do Pra-frentex Banking.",
                   subcommands = { AbrirContaCommand.class, ConsultarContaCommand.class })
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final CommandLine.IFactory factory;

    public ConsoleApplicationRunner(CommandLine.IFactory factory) {
        this.factory = factory;
    }

    @Override
    public void run(String... args) throws Exception {
        new CommandLine(this, factory).execute(args);
    }
}
