package com.example.sorting;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String dataFolder = "conjuntosDeDados";
        String reportFile = "relatorio.csv";

        System.out.println("Working directory (user.dir): " + System.getProperty("user.dir"));
        File folder = new File(dataFolder);
        System.out.println("Path usado: " + folder.getAbsolutePath());
        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println(">>> A pasta NÃO EXISTE ou não é um diretório!");
        } else {
            System.out.println(">>> Arquivos encontrados em '" + dataFolder + "':");
            for (File f : files) {
                System.out.println("    - " + f.getName());
            }
        }
        System.out.println("=======================================================");

        GenericList<DataSet> datasets;
        try {
            datasets = DataSet.loadFromFolder(dataFolder);
        } catch (IOException e) {
            System.err.println("Erro ao carregar datasets: " + e.getMessage());
            return;
        }

        GenericList<SortAlgorithm> algorithms = new GenericList<>();
        algorithms.add(new BubbleSort());
        algorithms.add(new InsertionSort());
        algorithms.add(new QuickSort());

        GenericList<Result> results = new GenericList<>();
        for (int i = 0; i < datasets.size(); i++) {
            DataSet ds = datasets.get(i);
            for (int j = 0; j < algorithms.size(); j++) {
                SortAlgorithm alg = algorithms.get(j);
                int[] copy = ds.getDataCopy();
                long start = System.nanoTime();
                alg.sort(copy);
                long elapsedMs = (System.nanoTime() - start) / 1_000_000;
                System.out.printf("%s | %s | %s | %d elementos -> %d ms%n",
                        alg.getName(),
                        ds.getName(),
                        ds.getType(),
                        ds.size(),
                        elapsedMs);
                results.add(new Result(
                        alg.getName(),
                        ds.getName(),
                        ds.getType(),
                        ds.size(),
                        elapsedMs
                ));
            }
        }

        ReportGenerator.generateCsvReport(results, reportFile);
    }
}
