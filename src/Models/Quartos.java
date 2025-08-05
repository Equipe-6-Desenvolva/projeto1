public class Quartos {
    private int numero;
    private boolean disponivel;

    public Quarto(int numero) {
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

    @Override
    public String toString() {
        return "Quarto " + numero + " – " + (disponivel ? "Livre" : "Ocupado");
    }
}