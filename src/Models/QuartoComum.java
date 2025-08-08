package Models;

public class QuartoComum extends Quartos {
    public QuartoComum(int numero) {
        super(numero);
    }

    @Override
    public boolean isVip() {
        return false;
    }

    @Override
    public String toString() {
        return "Quarto Comum " + getNumero() + " – " + (isDisponivel() ? "Livre" : "Ocupado");
    }
}

