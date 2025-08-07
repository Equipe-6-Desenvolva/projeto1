import Models.Avaliacao;
import Models.Cliente;
import View.AvaliacaoConsole;
import View.ClienteConsole;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new ClienteConsole().run();
        System.out.println("\nCadastro concluído:");
        System.out.println(cliente);

        AvaliacaoConsole console = new AvaliacaoConsole();
        Avaliacao avaliacao = console.run();
        System.out.println("\nAvaliação cadastrada:");
        System.out.println(avaliacao);
    }
}