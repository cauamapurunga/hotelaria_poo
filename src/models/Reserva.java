package models;

import models.Abstracts.Quarto;
import models.Abstracts.Transacao;
import models.Exceptions.QuartoIndisponivelException;
import models.Interfaces.Pagavel;

public class Reserva extends Transacao implements Pagavel {
    private final Quarto quarto;
    private final Cliente cliente;

    // Construtor "normal" - usado para criar reservas novas
    public Reserva(Quarto quarto, Cliente cliente) throws QuartoIndisponivelException {
        if (!quarto.isDisponivel()) {
            throw new QuartoIndisponivelException("O quarto " + quarto.getNumero() + " está indisponível para reserva.");
        }
        this.quarto = quarto;
        this.cliente = cliente;
        quarto.setDisponivel(false);
    }

    public Reserva(Quarto quarto, Cliente cliente, boolean ignorarDisponibilidade) {
        this.quarto = quarto;
        this.cliente = cliente;
        quarto.setDisponivel(false);
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void pagar() throws Exception { }

    @Override
    public void executar() {
        System.out.println("Reserva executada para o cliente: " + cliente.getNome());
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "quarto=" + quarto +
                ", cliente=" + cliente +
                '}';
    }
}