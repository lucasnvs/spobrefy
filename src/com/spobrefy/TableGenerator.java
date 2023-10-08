package com.spobrefy;

import java.util.ArrayList;

public class TableGenerator {
    public static String createTable(ArrayList<String> cabecalho, ArrayList<ArrayList<String>> conteudo) {
        StringBuilder tabela = new StringBuilder();

        // Verifica se os tamanhos das listas são compatíveis
        if (cabecalho.size() == 0 || conteudo.size() == 0 || cabecalho.size() != conteudo.get(0).size()) {
            return "Erro: Tamanhos de cabeçalho e conteúdo incompatíveis.";
        }

        // Calcula a largura das colunas
        int[] largurasColunas = new int[cabecalho.size()];
        for (int i = 0; i < cabecalho.size(); i++) {
            largurasColunas[i] = cabecalho.get(i).length();
            for (ArrayList<String> linha : conteudo) {
                if (linha.get(i).length() > largurasColunas[i]) {
                    largurasColunas[i] = linha.get(i).length();
                }
            }
        }

        // Gera o cabeçalho
        for (int i = 0; i < cabecalho.size(); i++) {
            if(i == 0) { tabela.append("| "); }
            tabela.append(String.format("%-" + largurasColunas[i] + "s", cabecalho.get(i))).append(" | ");
        }
        tabela.append("\n");

        // Gera as linhas de conteúdo
        for (ArrayList<String> linha : conteudo) {
            for (int i = 0; i < linha.size(); i++) {
                if(i == 0) { tabela.append("| "); }
                tabela.append(String.format("%-" + largurasColunas[i] + "s", linha.get(i))).append(" | ");
            }
            tabela.append("\n");
        }

        return tabela.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> cabecalho = new ArrayList<>();
        cabecalho.add("Nome");
        cabecalho.add("Idade");
        cabecalho.add("Cargo");

        ArrayList<ArrayList<String>> conteudo = new ArrayList<>();
        ArrayList<String> linha1 = new ArrayList<>();
        linha1.add("João");
        linha1.add("30");
        linha1.add("Desenvolvedor");
        conteudo.add(linha1);

        ArrayList<String> linha2 = new ArrayList<>();
        linha2.add("Maria");
        linha2.add("28");
        linha2.add("Designer");
        conteudo.add(linha2);

        ArrayList<String> linha3 = new ArrayList<>();
        linha3.add("José");
        linha3.add("35");
        linha3.add("Gerente");
        conteudo.add(linha3);

        String tabela = createTable(cabecalho, conteudo);
        System.out.println(tabela);
    }
}