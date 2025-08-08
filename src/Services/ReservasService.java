package Services;

import Models.Cliente;
import Models.Hotel;
import Models.Quartos;
import Models.Reservas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReservasService {
    private List<Reservas> reservas = new ArrayList<>();

    public Reservas criarReserva(Cliente cliente, Hotel hotel, Quartos quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        if (!quarto.isDisponivel()) {
            System.out.println("Esse quarto não está disponível!");
            return null;
        }
        Integer idReserva = new Random().nextInt(1_000_000);
        Reservas reserva = new Reservas(idReserva, cliente, hotel, quarto, dataEntrada, dataSaida, "ATIVA");
        reservas.add(reserva);
        quarto.setDisponivel(false);
        System.out.println("Reserva criada com sucesso!");
        return reserva;
    }

    public List<Reservas> listarReservas() {
        return reservas;
    }

    public void cancelarReserva(Reservas reserva) {
        reserva.setStatus("CANCELADA");
        reserva.getQuarto().setDisponivel(true);
        System.out.println("Reserva cancelada!");
    }
}

