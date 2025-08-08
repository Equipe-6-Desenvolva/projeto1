package Models;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private List<Quartos> quartos = new ArrayList<>();

    public Hotel(String nome) {
        this.nome = nome;
    }

    public void adicionarQuarto(Quartos q) {
        quartos.add(q);
    }

    public List<Quartos> getQuartosLivres() {
        List<Quartos> livres = new ArrayList<>();
        for (Quartos q : quartos) {
            if (q.isDisponivel()) livres.add(q);
        }
        return livres;
    }

    public List<Quartos> getQuartos() {
        return quartos;
    }

    @Override
    public String toString() {
        return nome + " – " + quartos.size() + " quartos";
    }
}