package models.Abstracts;

public abstract class Quarto {
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

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public abstract double calcularPreco();

    public double calcularPrecoFinal() {
        return calcularPreco();
    }

    public double calcularPrecoFinal(double desconto) {
        return calcularPreco() - desconto;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "numero=" + numero +
                ", disponivel=" + disponivel +
                '}';
    }
}
