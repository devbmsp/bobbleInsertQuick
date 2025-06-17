package com.example.sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataSet {
    public enum Type { ALEATORIO, CRESCENTE, DECRESCENTE }

    private final String name;
    private final Type   type;
    private final GenericList<Integer> data;

    public DataSet(String name, Type type, GenericList<Integer> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName()    { return name; }
    public Type   getType()    { return type; }
    public int    size()       { return data.size(); }

    public GenericList<Integer> getDataCopy() {
        GenericList<Integer> copy = new GenericList<>();
        for (int i = 0; i < data.size(); i++) {
            copy.add(data.get(i));
        }
        return copy;
    }


    public static GenericList<DataSet> loadFromFolder(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        GenericList<DataSet> list = new GenericList<>();

        if (files != null) {
            for (File f : files) {
                if (!f.isFile() || !f.getName().toLowerCase().endsWith(".csv")) {
                    continue;
                }

                String fn = f.getName();
                Type t = fn.toLowerCase().contains("aleatorio") ? Type.ALEATORIO
                        : fn.toLowerCase().contains("crescente") ? Type.CRESCENTE
                        :                                           Type.DECRESCENTE;

                GenericList<Integer> nums = new GenericList<>();
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    try {
                        nums.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                        // pula cabeçalho
                    }
                }
                br.close();

                list.add(new DataSet(fn, t, nums));
            }
        }

        return list;
    }
}
