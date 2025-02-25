package Services;

import models.*;
import models.Abstracts.*;
import models.Exceptions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MenuService {
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();
    private final HashSet<Quarto> quartos = new HashSet<>();
    private final Scanner scanner = new Scanner(System.in);
    private final ArquivoService arquivoService = new ArquivoService();
    private final String DATA_PACKAGE = Paths.get("src", "models", "Data").toString();

    public MenuService() {
        carregarDados();
    }

    public void exibirMenu() {
        while (true) {
            try {
                System.out.println("\n<-- HOTELARIA SANTOS -->");
                System.out.println("1- Cadastrar");
                System.out.println("2- Listar");
                System.out.println("3- Fazer Reserva");
                System.out.println("4- Processar Pagamento");
                System.out.println("5- Remover");
                System.out.println("6- Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        menuCadastrar();
                        break;
                    case 2:
                        menuListar();
                        break;
                    case 3:
                        fazerReserva();
                        break;
                    case 4:
                        processarPagamento();
                        break;
                    case 5:
                        remover();
                        break;
                    case 6:
                        salvarDados();
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void menuCadastrar() {
        System.out.println("\n<-- CADASTRAR -->");
        System.out.println("1- Cliente");
        System.out.println("2- Funcionário");
        System.out.println("3- Quarto");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarFuncionario();
                break;
            case 3:
                cadastrarQuarto();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void menuListar() {
        System.out.println("\n<-- LISTAR -->");
        System.out.println("1- Clientes");
        System.out.println("2- Funcionários");
        System.out.println("3- Reservas");
        System.out.println("4- Quartos");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                listarClientes();
                break;
            case 2:
                listarFuncionarios();
                break;
            case 3:
                listarReservas();
                break;
            case 4:
                listarQuartos();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarCliente() {
        try {
            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o documento do cliente: ");
            String documento = scanner.nextLine();
            clientes.add(new Cliente(nome, documento));
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void cadastrarFuncionario() {
        try {
            System.out.print("Digite o nome do funcionário: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o documento do funcionário: ");
            String documento = scanner.nextLine();
            funcionarios.add(new Funcionario(nome, documento));
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    private void cadastrarQuarto() {
        try {
            System.out.print("Digite o número do quarto: ");
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.print("Digite o tipo do quarto (1 para Luxo, 2 para Simples): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            if (tipo == 1) {
                quartos.add(new QuartoLuxo(numero));
            } else if (tipo == 2) {
                quartos.add(new QuartoSimples(numero));
            } else {
                System.out.println("Tipo de quarto inválido!");
                return;
            }

            System.out.println("Quarto cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar quarto: " + e.getMessage());
        }
    }

    private void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\nClientes cadastrados:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\nFuncionários cadastrados:");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            System.out.println("\nReservas registradas:");
            for (Reserva reserva : reservas) {
                System.out.println("Quarto: " + reserva.getQuarto().getNumero() + ", Cliente: " + reserva.getCliente().getNome());
            }
        }
    }

    private void listarQuartos() {
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado.");
        } else {
            System.out.println("\nQuartos cadastrados:");
            for (Quarto quarto : quartos) {
                String tipo = (quarto instanceof QuartoLuxo) ? "Luxo" : "Simples";
                System.out.println("Número: " + quarto.getNumero() + ", Disponível: " + (quarto.isDisponivel() ? "Sim" : "Não") + ", Tipo: " + tipo);
            }
        }
    }

    private void fazerReserva() {
        try {
            System.out.print("Digite o documento do cliente: ");
            String documento = scanner.nextLine();
            Cliente cliente = buscarCliente(documento);

            System.out.print("Digite o número do quarto para reserva: ");
            int numeroQuarto = Integer.parseInt(scanner.nextLine());

            Quarto quartoEscolhido = buscarQuartoDisponivel(numeroQuarto);

            Reserva reserva = new Reserva(quartoEscolhido, cliente);
            reservas.add(reserva);
            System.out.println("Reserva realizada com sucesso!");
            System.out.println(reserva);
        } catch (ClienteNaoEncontradoException | QuartoIndisponivelException e) {
            System.out.println("Erro na reserva: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número para o quarto.");
        }
    }

    private void processarPagamento() {
        try {
            System.out.print("Digite o valor do pagamento: ");
            double valor = Double.parseDouble(scanner.nextLine());
            System.out.println("Pagamento de R$" + valor + " processado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor inválido para pagamento.");
        }
    }

    private void remover() {
        try {
            System.out.println("\n<-- REMOVER -->");
            System.out.println("1- Cliente");
            System.out.println("2- Funcionário");
            System.out.println("3- Quarto");
            System.out.println("4- Reserva");
            System.out.print("Escolha o tipo para remover: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    removerCliente();
                    break;
                case 2:
                    removerFuncionario();
                    break;
                case 3:
                    removerQuarto();
                    break;
                case 4:
                    removerReserva();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
        }
    }

    private void removerCliente() {
        System.out.print("Digite o documento do cliente para remover: ");
        String documento = scanner.nextLine();
        Cliente clienteParaRemover = null;
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                clienteParaRemover = cliente;
                break;
            }
        }
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
            System.out.println("Cliente removido.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void removerFuncionario() {
        System.out.print("Digite o documento do funcionário para remover: ");
        String documento = scanner.nextLine();
        Funcionario funcionarioParaRemover = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDocumento().equals(documento)) {
                funcionarioParaRemover = funcionario;
                break;
            }
        }
        if (funcionarioParaRemover != null) {
            funcionarios.remove(funcionarioParaRemover);
            System.out.println("Funcionário removido.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void removerQuarto() {
        System.out.print("Digite o número do quarto para remover: ");
        int numero = Integer.parseInt(scanner.nextLine());
        Quarto quartoParaRemover = null;
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                quartoParaRemover = quarto;
                break;
            }
        }
        if (quartoParaRemover != null) {
            quartos.remove(quartoParaRemover);
            System.out.println("Quarto removido.");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    private void removerReserva() {
        System.out.print("Digite o número do quarto da reserva para remover: ");
        int numeroQuarto = Integer.parseInt(scanner.nextLine());
        Reserva reservaParaRemover = null;
        for (Reserva reserva : reservas) {
            if (reserva.getQuarto().getNumero() == numeroQuarto) {
                reservaParaRemover = reserva;
                break;
            }
        }
        if (reservaParaRemover != null) {
            reservas.remove(reservaParaRemover);
            System.out.println("Reserva removida.");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private Cliente buscarCliente(String documento) throws ClienteNaoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente com documento " + documento + " não encontrado.");
    }

    private Quarto buscarQuartoDisponivel(int numeroQuarto) throws QuartoIndisponivelException {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numeroQuarto && quarto.isDisponivel()) {
                return quarto;
            }
        }
        throw new QuartoIndisponivelException("Quarto " + numeroQuarto + " não está disponível ou não existe.");
    }

    private Quarto buscarQuartoPorNumero(int numeroQuarto) throws QuartoIndisponivelException {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numeroQuarto) {
                return quarto;
            }
        }
        throw new QuartoIndisponivelException("Quarto com número " + numeroQuarto + " não encontrado.");
    }

    private void carregarDados() {
        try {
            ArrayList<String> clientesDados = arquivoService.ler(DATA_PACKAGE + "/clientes.txt");
            for (String linha : clientesDados) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    clientes.add(new Cliente(partes[0], partes[1]));
                }
            }

            ArrayList<String> funcionariosDados = arquivoService.ler(DATA_PACKAGE + "/funcionarios.txt");
            for (String linha : funcionariosDados) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    funcionarios.add(new Funcionario(partes[0], partes[1]));
                }
            }

            ArrayList<String> quartosDados = arquivoService.ler(DATA_PACKAGE + "/quartos.txt");
            for (String linha : quartosDados) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    int numero = Integer.parseInt(partes[0]);
                    String tipo = partes[1];
                    boolean disponivel = Boolean.parseBoolean(partes[2]);

                    Quarto quarto = tipo.equals("Luxo") ? new QuartoLuxo(numero) : new QuartoSimples(numero);
                    quarto.setDisponivel(disponivel);
                    quartos.add(quarto);
                }
            }

            ArrayList<String> reservasDados = arquivoService.ler(DATA_PACKAGE + "/reservas.txt");
            for (String linha : reservasDados) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int numeroQuarto = Integer.parseInt(partes[0]);
                    String documentoCliente = partes[1];

                    Cliente cliente = buscarCliente(documentoCliente);
                    Quarto quarto = buscarQuartoPorNumero(numeroQuarto);

                    Reserva reserva = new Reserva(quarto, cliente);
                    reservas.add(reserva);
                    quarto.setDisponivel(false);
                }
            }

            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            ArrayList<String> clientesDados = new ArrayList<>();
            for (Cliente cliente : clientes) {
                clientesDados.add(cliente.getNome() + ";" + cliente.getDocumento());
            }
            arquivoService.salvar(DATA_PACKAGE + "/clientes.txt", clientesDados);

            ArrayList<String> funcionariosDados = new ArrayList<>();
            for (Funcionario funcionario : funcionarios) {
                funcionariosDados.add(funcionario.getNome() + ";" + funcionario.getDocumento());
            }
            arquivoService.salvar(DATA_PACKAGE + "/funcionarios.txt", funcionariosDados);

            ArrayList<String> quartosDados = new ArrayList<>();
            for (Quarto quarto : quartos) {
                quartosDados.add(quarto.getNumero() + ";" + (quarto instanceof QuartoLuxo ? "Luxo" : "Simples") + ";" + quarto.isDisponivel());
            }
            arquivoService.salvar(DATA_PACKAGE + "/quartos.txt", quartosDados);

            ArrayList<String> reservasDados = new ArrayList<>();
            for (Reserva reserva : reservas) {
                reservasDados.add(reserva.getQuarto().getNumero() + ";" + reserva.getCliente().getDocumento());
            }
            arquivoService.salvar(DATA_PACKAGE + "/reservas.txt", reservasDados);

            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}