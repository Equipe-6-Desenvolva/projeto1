package Models;

public class QuartoVip extends Quartos {
    public QuartoVip(int numero) {
        super(numero);
    }

    @Override
    public boolean isVip() {
        return true;
    }

    @Override
    public String toString() {
        return "Quarto VIP " + getNumero() + " – " + (isDisponivel() ? "Livre" : "Ocupado");
    }
}

