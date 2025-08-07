package View;

import Models.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ClienteConsole {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Executa o fluxo completo de preenchimento e retorna o Cliente populado.
     */
    public Cliente run() {
        Cliente cliente = new Cliente();  // usa construtor padrão

        System.out.println("== Cadastro de Cliente ==");

        // Nome
        System.out.print("Nome: ");
        cliente.setNome(sc.nextLine().trim());

        // Sobrenome
        System.out.print("Sobrenome: ");
        cliente.setSobrenome(sc.nextLine().trim());

        // Documento (11 dígitos)
        while (true) {
            System.out.print("Documento (11 dígitos): ");
            String doc = sc.nextLine().trim();
            try {
                cliente.setDocumento(doc);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ERRO: " + e.getMessage());
            }
        }

        // Email
        while (true) {
            System.out.print("Email: ");
            String email = sc.nextLine().trim();
            try {
                cliente.setEmail(email);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ERRO: " + e.getMessage());
            }
        }

        // Data de Nascimento
        while (true) {
            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            String input = sc.nextLine().trim();
            try {
                LocalDate dt = LocalDate.parse(input);
                cliente.setDtNascimento(dt);
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("  ERRO: formato inválido. Use AAAA-MM-DD.");
            } catch (IllegalArgumentException ex) {
                System.out.println("  ERRO: " + ex.getMessage());
            }
        }

        // Senha
        while (true) {
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            try {
                cliente.setSenha(senha);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ERRO: " + e.getMessage());
            }
        }

        return cliente;
    }
}
