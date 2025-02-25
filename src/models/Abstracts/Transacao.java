package models.Abstracts;

public abstract class Transacao {
    public abstract void executar();

    @Override
    public String toString() {
        return "Transacao{}";
    }
}