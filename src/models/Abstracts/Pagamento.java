    package models.Abstracts;

    public abstract class Pagamento {
        public abstract boolean processarPagamento(double valor);

        @Override
        public String toString() {
            return "Pagamento{}";
        }
    }