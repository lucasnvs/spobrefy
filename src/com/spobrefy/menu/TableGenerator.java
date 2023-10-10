package com.spobrefy.menu;

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
}