package Models;
public abstract class Quartos {
    private int numero;
    private boolean disponivel;

    public Quartos(int numero) {
        this.numero = numero;
        this.disponivel = true;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean ok) {
        this.disponivel = ok;
    }

    public abstract boolean isVip();

    @Override
    public String toString() {
        return "Quarto " + numero + " – " + (disponivel ? "Livre" : "Ocupado");
    }
}