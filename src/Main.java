import Models.*;
import Services.ReservasService;
import View.AvaliacaoConsole;
import java.time.LocalDate;
import java.util.Scanner;
import View.ClienteConsole;

public class Main {
    private static ReservasService reservasServiceGlobal = new ReservasService();

    private static java.util.List<Cliente> clientes = new java.util.ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel("Hotel AdaTech");
        hotel.adicionarQuarto(new QuartoComum(101));
        hotel.adicionarQuarto(new QuartoComum(102));
        hotel.adicionarQuarto(new QuartoComum(103));

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Menu Reservas");
            System.out.println("2. Menu Cliente");
            System.out.println("3. Menu Quartos");
            System.out.println("4. Cadastrar Avaliação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    menuReservas(hotel, scanner);
                    break;
                case "2":
                    menuCliente(scanner);
                    break;
                case "3":
                    menuQuartos(hotel, scanner);
                    break;
                case "4":
                    cadastrarAvaliacao(scanner);
                    break;
                case "0":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }



    private static void cadastrarReserva(Hotel hotel, Scanner scanner) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente antes de criar uma reserva.");
            return;
        }
        System.out.println("\n--- Clientes Cadastrados ---");
        int i = 1;
        for (Cliente c : clientes) {
            System.out.println(i + ". " + c.getNome() + " " + c.getSobrenome());
            i++;
        }
        System.out.print("Selecione o número do cliente para a reserva: ");
        String inputCliente = scanner.nextLine();
        int idxCliente;
        try {
            idxCliente = Integer.parseInt(inputCliente) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
            return;
        }
        if (idxCliente < 0 || idxCliente >= clientes.size()) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        Cliente cliente = clientes.get(idxCliente);

        System.out.println("\nQuartos disponíveis:");
        for (Quartos q : hotel.getQuartosLivres()) {
            System.out.println("Quarto: " + q.getNumero());
        }

        Quartos quartoSelecionado = null;
        while (quartoSelecionado == null) {
            System.out.print("Digite o número do quarto desejado: ");
            String input = scanner.nextLine();
            int numeroQuarto;
            try {
                numeroQuarto = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                continue;
            }
            for (Quartos q : hotel.getQuartosLivres()) {
                if (q.getNumero() == numeroQuarto) {
                    quartoSelecionado = q;
                    break;
                }
            }
            if (quartoSelecionado == null) {
                System.out.println("Quarto não disponível! Escolha novamente.");
            }
        }

        LocalDate hoje = LocalDate.now();
        LocalDate dataEntrada = null;
        LocalDate dataSaida = null;
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de entrada (DD/MM/AAAA): ");
            String entradaStr = scanner.nextLine();
            System.out.print("Digite a data de saída (DD/MM/AAAA): ");
            String saidaStr = scanner.nextLine();
            try {
                dataEntrada = LocalDate.parse(entradaStr, formatter);
                dataSaida = LocalDate.parse(saidaStr, formatter);
            } catch (Exception e) {
                System.out.println("Data inválida! Tente novamente.");
                continue;
            }
            if (dataEntrada.isBefore(hoje)) {
                System.out.println("A data de entrada deve ser hoje ou uma data futura.");
                continue;
            }
            if (dataSaida.isBefore(dataEntrada)) {
                System.out.println("A data de saída deve ser igual ou posterior à data de entrada.");
                continue;
            }
            break;
        }

        Reservas reserva = reservasServiceGlobal.criarReserva(cliente, hotel, quartoSelecionado, dataEntrada, dataSaida);

        if (reserva != null) {
            System.out.println("\nReserva criada:");
            System.out.println(reserva);
        } else {
            System.out.println("\nNão foi possível criar a reserva.");
        }
    }

    private static void cadastrarQuarto(Hotel hotel, Scanner scanner) {
        System.out.print("Digite o número do novo quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());
        boolean existe = false;
        for (Quartos q : hotel.getQuartos()) {
            if (q.getNumero() == numero) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Já existe um quarto com esse número!");
        } else {
            System.out.print("O quarto é VIP? (s/n): ");
            String vip = scanner.nextLine().trim().toLowerCase();
            Quartos novoQuarto;
            if (vip.equals("s")) {
                novoQuarto = new QuartoVip(numero);
            } else {
                novoQuarto = new QuartoComum(numero);
            }
            hotel.adicionarQuarto(novoQuarto);
            System.out.println("Quarto adicionado com sucesso!");
        }
    }

    private static void cadastrarAvaliacao(Scanner scanner) {
        AvaliacaoConsole console = new AvaliacaoConsole();
        Avaliacao avaliacao = console.run();
        System.out.println("\nAvaliação cadastrada:");
        System.out.println(avaliacao);
    }

    private static void consultarReservas() {
        System.out.println("\n--- Reservas Cadastradas ---");
        for (Reservas r : reservasServiceGlobal.listarReservas()) {
            System.out.println(r);
        }
        if (reservasServiceGlobal.listarReservas().isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        }
    }

    private static void menuReservas(Hotel hotel, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Reservas ---");
            System.out.println("1. Cadastrar Reserva");
            System.out.println("2. Consultar Reservas");
            System.out.println("3. Cancelar Reserva");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastrarReserva(hotel, scanner);
                    break;
                case "2":
                    consultarReservas();
                    break;
                case "3":
                    cancelarReserva(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cancelarReserva(Scanner scanner) {
        System.out.println("\n--- Cancelar Reserva ---");
        consultarReservas();
        System.out.print("Digite o ID da reserva para cancelar: ");
        String input = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }
        Reservas reservaParaCancelar = null;
        for (Reservas r : reservasServiceGlobal.listarReservas()) {
            if (r.getIdReserva() == id && r.getStatus().equals("ATIVA")) {
                reservaParaCancelar = r;
                break;
            }
        }
        if (reservaParaCancelar == null) {
            System.out.println("Reserva não encontrada ou já cancelada.");
            return;
        }
        reservaParaCancelar.setStatus("CANCELADA");
        reservaParaCancelar.getQuarto().setDisponivel(true);
        System.out.println("Reserva cancelada com sucesso!");
    }

    private static void menuCliente(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Cliente ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Clientes");
            System.out.println("3. Editar Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastrarCliente(scanner);
                    break;
                case "2":
                    consultarClientes();
                    break;
                case "3":
                    editarCliente(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        Cliente cliente = new ClienteConsole().run();
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void consultarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        int i = 1;
        for (Cliente c : clientes) {
            System.out.println(i + ". " + c.getNome() + " " + c.getSobrenome());
            i++;
        }
    }

    private static void editarCliente(Scanner scanner) {
        consultarClientes();
        if (clientes.isEmpty()) return;
        System.out.print("Digite o número do cliente para editar: ");
        String input = scanner.nextLine();
        int posicao = Integer.parseInt(input) - 1;
        if (posicao < 0 || posicao >= clientes.size()) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        Cliente cliente = clientes.get(posicao);
        System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) cliente.setNome(novoNome);
        System.out.print("Novo sobrenome (atual: " + cliente.getSobrenome() + "): ");
        String novoSobrenome = scanner.nextLine();
        if (!novoSobrenome.trim().isEmpty()) cliente.setSobrenome(novoSobrenome);
        System.out.println("Cliente atualizado!");
    }

    private static void menuQuartos(Hotel hotel, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Quartos ---");
            System.out.println("1. Adicionar Quarto");
            System.out.println("2. Consultar Quartos Disponíveis");
            System.out.println("3. Consultar Todos os Quartos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastrarQuarto(hotel, scanner);
                    break;
                case "2":
                    consultarQuartosDisponiveis(hotel);
                    break;
                case "3":
                    consultarTodosQuartos(hotel);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void consultarQuartosDisponiveis(Hotel hotel) {
        System.out.println("\n--- Quartos Disponíveis ---");
        if (hotel.getQuartosLivres().isEmpty()) {
            System.out.println("Nenhum quarto disponível.");
            return;
        }
        for (Quartos q : hotel.getQuartosLivres()) {
            System.out.println("Quarto: " + q.getNumero());
        }
    }

    private static void consultarTodosQuartos(Hotel hotel) {
        System.out.println("\n--- Todos os Quartos ---");
        if (hotel.getQuartos().isEmpty()) {
            System.out.println("Nenhum quarto cadastrado.");
            return;
        }
        for (Quartos q : hotel.getQuartos()) {
            String tipo = q.isVip() ? "VIP" : "Comum";
            System.out.println("Quarto: " + q.getNumero() + " [" + tipo + "]" + (q.isDisponivel() ? " (Disponível)" : " (Ocupado)"));
        }
    }
}