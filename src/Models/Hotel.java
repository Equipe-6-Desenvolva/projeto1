import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private List<Quarto> quartos = new ArrayList<>();

    public Hotel(String nome) {
        this.nome = nome;
    }

    public void adicionarQuarto(Quarto q) {
        quartos.add(q);
    }

    public List<Quarto> getQuartosLivres() {
        List<Quarto> livres = new ArrayList<>();
        for (Quarto q : quartos) {
            if (q.isDisponivel()) livres.add(q);
        }
        return livres;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    @Override
    public String toString() {
        return nome + " – " + quartos.size() + " quartos";
    }
}