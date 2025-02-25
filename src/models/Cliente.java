package models;

import models.Abstracts.Usuario;
import models.Exceptions.ClienteNaoEncontradoException;
import models.Interfaces.Cadastro;

public class Cliente extends Usuario implements Cadastro {

    public Cliente(String nome, String documento) {
        super(nome, documento);
    }

    public static void validarCliente(String documento, Cliente cliente) throws ClienteNaoEncontradoException {
        if (!cliente.getDocumento().equals(documento)) {
            throw new ClienteNaoEncontradoException("Cliente com documento " + documento + " não foi encontrado.");
        }
    }

    @Override
    public void cadastrar() throws Exception {
        System.out.println("Cliente Cadastrado.");
    }

    public void exibirDados() {
        System.out.println("Nome: " + getNome());
    }

    public void exibirDados(boolean mostrarDocumento) {
        System.out.println("Nome: " + getNome());
        if (mostrarDocumento) {
            System.out.println("Documento: " + getDocumento());
        }
    }
}