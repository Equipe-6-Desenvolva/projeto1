package Models;

import java.time.LocalDate;

public class Reservas {
    private Integer idReserva;
    private Cliente cliente;
    private Hotel hotel;
    private Quartos quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String status;

    public Reservas(Integer idReserva, Cliente cliente, Hotel hotel, Quartos quarto, LocalDate dataEntrada, LocalDate dataSaida, String status) {
        if (idReserva == null) {
            System.out.println("idReserva não pode ser nulo");
        }
        if (cliente == null) {
            System.out.println("cliente não pode ser nulo");
        }
        if (hotel == null) {
            System.out.println("hotel não pode ser nulo");
        }
        if (quarto == null) {
            System.out.println("quarto não pode ser nulo");
        }
        if (dataEntrada == null) {
            System.out.println("dataEntrada não pode ser nulo");
        }
        if (dataSaida == null) {
            System.out.println("dataSaida não pode ser nulo");
        }
        if (status == null) {
            System.out.println("status não pode ser nulo");
        }
        if (dataEntrada != null && dataSaida != null && dataSaida.isBefore(dataEntrada)) {
            System.out.println("Data de saída não pode ser antes da data de entrada");
        }
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.hotel = hotel;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = status;
    }

    public Integer getIdReserva() { return idReserva; }
    public Cliente getCliente() { return cliente; }
    public Hotel getHotel() { return hotel; }
    public Quartos getQuarto() { return quarto; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}