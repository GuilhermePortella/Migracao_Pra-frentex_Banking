package br.infrastructure.cli.commands;

import br.application.service.ConsultaContaService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "consultar-conta", description = "Verifica se uma conta existe com base na agência e número.")
public class ConsultarContaCommand implements Runnable {

    private final ConsultaContaService consultaContaService;

    public ConsultarContaCommand(ConsultaContaService consultaContaService) {
        this.consultaContaService = consultaContaService;
    }

    @Option(names = {"--agencia"}, description = "Número da agência", required = true)
    private String agencia;

    @Option(names = {"--conta"}, description = "Número da conta", required = true)
    private String conta;

    @Override
    public void run() {
        boolean existe = consultaContaService.contaExiste(agencia, conta);
        if (existe) {
            System.out.printf("\nCONFIRMADO: A conta %s da agência %s foi encontrada.%n", conta, agencia);
        } else {
            System.err.printf("\nAVISO: A conta %s da agência %s NÃO foi encontrada.%n", conta, agencia);
        }
    }
}
