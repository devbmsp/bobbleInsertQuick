package com.example.sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataSet {
    public enum Type { ALEATORIO, CRESCENTE, DECRESCENTE }

    private final String name;
    private final Type   type;
    private final int[]  data;

    public DataSet(String name, Type type, int[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName()    { return name; }
    public Type   getType()    { return type; }
    public int    size()       { return data.length; }

    /** Retorna uma cópia do vetor interno */
    public int[] getDataCopy() {
        int[] copy = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    /**
     * Carrega todos os CSVs de uma pasta, pulando linhas não numéricas.
     * Cada CSV deve conter um número por linha (possivelmente com cabeçalho).
     */
    public static GenericList<DataSet> loadFromFolder(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        GenericList<DataSet> list = new GenericList<>();

        if (files != null) {
            for (File f : files) {
                if (!f.isFile() || !f.getName().toLowerCase().endsWith(".csv")) {
                    continue;
                }

                // Determina o tipo pelo nome do arquivo
                String fn = f.getName();
                String lower = fn.toLowerCase();
                Type t = lower.contains("aleatorio")   ? Type.ALEATORIO
                        : lower.contains("crescente")   ? Type.CRESCENTE
                        :                                  Type.DECRESCENTE;

                // Lê cada linha, tenta parseInt e pula cabeçalhos/linhas inválidas
                GenericList<Integer> nums = new GenericList<>();
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    try {
                        nums.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                        // pula linha que não seja inteiro
                    }
                }
                br.close();

                // Converte para int[]
                int[] arr = new int[nums.size()];
                for (int i = 0; i < nums.size(); i++) {
                    arr[i] = nums.get(i);
                }

                // Adiciona ao retorno
                list.add(new DataSet(fn, t, arr));
            }
        }

        return list;
    }
}
