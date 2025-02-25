package models;

import models.Abstracts.Quarto;
import models.Interfaces.Reservavel;

public class QuartoSimples extends Quarto implements Reservavel {
    public QuartoSimples(int numero) {
        super(numero);
    }

    @Override
    public void reservar() throws Exception {
        System.out.println("Reservado.");
    }

    @Override
    public double calcularPreco() {
        return 100.0;
    }
}