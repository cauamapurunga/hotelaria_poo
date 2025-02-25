package Services;

import java.io.*;
import java.util.ArrayList;

public class ArquivoService {

    public void salvar(String nomeArquivo, ArrayList<String> dados) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (String dado : dados) {
                writer.write(dado);
                writer.newLine();
            }
        }
    }

    public ArrayList<String> ler(String nomeArquivo) throws IOException {
        ArrayList<String> dados = new ArrayList<>();
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return dados;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                dados.add(linha);
            }
        }
        return dados;
    }
}