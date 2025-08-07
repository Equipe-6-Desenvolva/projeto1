package View;

import Models.Avaliacao;

import java.time.LocalDate;
import java.util.Scanner;

public class AvaliacaoConsole {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Executa o fluxo completo de preenchimento e retorna a Avaliacao.
     */
    public Avaliacao run() {
        System.out.println("== Cadastro de Avaliação ==");

        // ID da reserva
        Integer idReserva = null;
        while (idReserva == null) {
            System.out.print("ID da Reserva: ");
            String input = sc.nextLine().trim();
            try {
                idReserva = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ERRO: ID inválido. Informe um número inteiro.");
            }
        }

        // Data da avaliação
        LocalDate dtAvaliacao = LocalDate.now();

        // Comentário
        String comentario = null;
        while (comentario == null) {
            System.out.print("Comentário (mínimo 20 caracteres): ");
            String input = sc.nextLine().trim();
            try {
                new Avaliacao(idReserva, dtAvaliacao, input, 1).setComentario(input);
                comentario = input;
            } catch (IllegalArgumentException e) {
                System.out.println("  ERRO: " + e.getMessage());
            }
        }

        // Estrelas
        Integer estrelas = null;
        while (estrelas == null) {
            System.out.print("Número de Estrelas (1 a 5): ");
            String input = sc.nextLine().trim();
            try {
                Integer valor = Integer.parseInt(input);
                new Avaliacao(idReserva, dtAvaliacao, comentario, valor).setEstrelas(valor);
                estrelas = valor;
            } catch (NumberFormatException e) {
                System.out.println("  ERRO: informe um número inteiro.");
            } catch (IllegalArgumentException e) {
                System.out.println("  ERRO: " + e.getMessage());
            }
        }

        return new Avaliacao(idReserva, dtAvaliacao, comentario, estrelas);
    }
}
