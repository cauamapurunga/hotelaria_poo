package models;

import models.Abstracts.Quarto;
import models.Abstracts.Transacao;
import models.Exceptions.QuartoIndisponivelException;
import models.Interfaces.Pagavel;

public class Reserva extends Transacao implements Pagavel {
    private final Quarto quarto;
    private final Cliente cliente;

    public Reserva(Quarto quarto, Cliente cliente) throws QuartoIndisponivelException {
        if (!quarto.isDisponivel()) {
            throw new QuartoIndisponivelException("O quarto " + quarto.getNumero() + " está indisponível para reserva.");
        }
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

    public void detalharReserva() {
        System.out.println("Quarto: " + quarto.getNumero());
    }

    public void detalharReserva(boolean incluirCliente) {
        System.out.println("Quarto: " + quarto.getNumero());
        if (incluirCliente) {
            System.out.println("Cliente: " + cliente.getNome());
        }
    }

    @Override
    public void pagar() throws Exception {

    }

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