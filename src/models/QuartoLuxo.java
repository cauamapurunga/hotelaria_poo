package models;

import models.Abstracts.Quarto;
import models.Interfaces.Reservavel;

public class QuartoLuxo extends Quarto implements Reservavel {
    public QuartoLuxo(int numero) {
        super(numero);
    }

    @Override
    public void reservar() throws Exception {
        System.out.println("Reservado.");
    }

    @Override
    public double calcularPreco() {
        return 300.0;
    }
}