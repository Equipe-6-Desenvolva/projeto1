package Models;


import java.time.LocalDate;
import java.util.Random;

public class Avaliacao {
        private final Integer id_Avaliacao;
        private final Integer id_Reserva; //TODO: como o ID da reserva já esta atrelado a um usuario e um hotel nas reservas, acho desnecessário conter os mesmos IDs nessa classe caso deseja seguir a lógica do modelo relacional basta descomentar as linhas
        private Integer estrelas;
        private final LocalDate dt_Avaliacao;
        private String comentario;
        //private final Integer id_Hotel;
        //private final Integer id_Clien    te;

        //Getters
        public Integer getId_Avaliacao() {return id_Avaliacao;}
        public Integer getId_Reserva() {return id_Reserva;}
        public LocalDate getDt_Avaliacao() {return dt_Avaliacao;}
        public String getComentario() {return comentario;}
        public Integer getEstrelas() {return estrelas;}
//    public Integer getId_Hotel() {return id_Hotel;}
//    public Integer getId_Cliente() {return id_Cliente;}

        //Setters
        public void setComentario(String comentario) {
            if (comentario == null || comentario.length() > 20) {
                throw new IllegalArgumentException("Comentário inválido ou excede o limite de 20 caracteres.");
            }
            this.comentario = comentario;
        }
        public void setEstrelas(Integer estrelas){
            this.estrelas = estrelas;
        }

        //Métodos
        private int gerarId() {
            Random random = new Random();
            return random.nextInt(1_000_000);
        }//TODO: Cuidado pode acontecer de gerar IDs duplicados o ideal é que ao usar IDs eles sejam gerados dentro do banco para evitar esse tipo de problema

        @Override
        public String toString() {
            return "Validacoes{id=" + id_Avaliacao + ", idReserva=" + id_Reserva + ", dtAvaliacao=" + dt_Avaliacao +
                    ", comentario='" + (comentario != null ? comentario : "N/A") + ", Estrelas= " + estrelas + "/5 " + "}";
        }

        //Construtores
        public Avaliacao(Integer idReserva, Integer estrelas, String comentario){
            if (idReserva == null) {throw new IllegalArgumentException("O ID da reserva não pode ser nulo.");}
            if (estrelas == null || estrelas < 0 || estrelas > 5) {throw new IllegalArgumentException("Estrelas deve estar entre 0 e 5.");}
            if (comentario == null || comentario.length() > 20){throw new IllegalArgumentException("Comentário inválido ou excede o limite de 20 caracteres.");}

            this.id_Avaliacao = gerarId();
            this.id_Reserva = idReserva;
            this.estrelas = estrelas; //TODO: pode ser apagado essa ideia de estrela, sugiro seguir para casos que o usuário não queira fazer um comentário somente uma avaliação, necessario adicionar uma lógica na main para a quantidade de estrelas desejadas

            this.comentario = comentario;
            this.dt_Avaliacao = LocalDate.now();
//        this.id_Cliente = idCliente;
//        this.id_Hotel = idHotel; //TODO: se desejar seguir com os IDs(Cliente e Hotel) basta adicionar nos parâmetros

        }

        public Avaliacao(Integer idReserva, Integer estrelas){
            if (idReserva == null) {throw new IllegalArgumentException("O ID da reserva não pode ser nulo.");}
            if (estrelas == null || estrelas < 0 || estrelas > 5) {throw new IllegalArgumentException("Estrelas deve estar entre 0 e 5.");}
            this.id_Avaliacao = gerarId();
            this.id_Reserva = idReserva;
            this.estrelas = estrelas;
            this.dt_Avaliacao = LocalDate.now();
//        this.id_Cliente = idCliente;
//        this.id_Hotel = idHotel; //TODO: se desejar seguir com os IDs(Cliente e Hotel) basta adicionar nos parâmetros

        }

    }
}
