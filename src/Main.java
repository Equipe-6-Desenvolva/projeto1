import Models.Cliente;
import View.ClienteConsole;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new ClienteConsole().run();
        System.out.println("\nCadastro concluído:");
        System.out.println(cliente);
    }
}