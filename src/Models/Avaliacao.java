package Models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Avaliacao {
    private final Integer idAvaliacao;
    private final Integer idReserva;
    private final LocalDate dtAvaliacao;
    private String comentario;
    private Integer estrelas;

    //CONSTRUTORES

    /** Construtor principal, recebe idReserva, data, comentário e estrelas. */
    public Avaliacao(Integer idReserva, LocalDate dtAvaliacao, String comentario, Integer estrelas) {
        this.idAvaliacao = gerarIdAvaliacao();
        this.idReserva    = Objects.requireNonNull(idReserva,    "ID de reserva não pode ser nulo");
        this.dtAvaliacao  = Objects.requireNonNull(dtAvaliacao,  "Data de avaliação não pode ser nula");
        setComentario(comentario);
        setEstrelas(estrelas);
    }

    /** Construtor secundário, gera idReserva aleatório. */
    public Avaliacao(LocalDate dtAvaliacao, String comentario, Integer estrelas) {
        this(new Random().nextInt(1_000_000), dtAvaliacao, comentario, estrelas);
    }

    //GERAÇÃO DE ID

    private int gerarIdAvaliacao() {
        // TODO: idealmente o DB gera o ID para evitar duplicação
        return new Random().nextInt(1_000_000);
    }

    //VALIDADORES

    private boolean validaComentario(String comentario) {
        if (comentario == null || comentario.isBlank() || comentario.length() < 20) {
            throw new IllegalArgumentException(
                    "O comentário deve ter ao menos 20 caracteres");
        }
        return true;
    }

    private boolean validaEstrelas(Integer estrelas) {
        if (estrelas == null || estrelas < 1 || estrelas > 5) {
            throw new IllegalArgumentException(
                    "A avaliação deve conter no minímo 1 e no máximo 5 estrelas");
        }
        return true;
    }

    //SETTERS

    public void setComentario(String comentario) {
        if (validaComentario(comentario)) {
            this.comentario = comentario;
        }
    }

    public void setEstrelas(Integer estrelas) {
        if (validaEstrelas(estrelas)) {
            this.estrelas = estrelas;
        }
    }

    //GETTERS

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public LocalDate getDtAvaliacao() {
        return dtAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getEstrelas() {
        return estrelas;
    }


    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", idReserva=" + idReserva +
                ", dtAvaliacao=" + dtAvaliacao +
                ", comentario='" + comentario + '\'' +
                ", estrelas=" + estrelas +
                '}';
    }
}
