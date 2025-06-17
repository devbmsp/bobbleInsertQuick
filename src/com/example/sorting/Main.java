package com.example.sorting;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String dataFolder = "conjuntosDeDados";
        String reportFile = "relatorio.csv";

        System.out.println("Working dir: " + System.getProperty("user.dir"));
        File folder = new File(dataFolder);
        if (folder.listFiles() != null) {
            for (File f : folder.listFiles()) {
                System.out.println("  • " + f.getName());
            }
        }
        System.out.println("--------------------------------");

        GenericList<DataSet> datasets;
        try {
            datasets = DataSet.loadFromFolder(dataFolder);
        } catch (IOException e) {
            System.err.println("Erro ao carregar datasets: " + e.getMessage());
            return;
        }

        GenericList<SortAlgorithm> algs = new GenericList<>();
        algs.add(new BubbleSort());
        algs.add(new InsertionSort());
        algs.add(new QuickSort());

        GenericList<Result> results = new GenericList<>();
        for (int i = 0; i < datasets.size(); i++) {
            DataSet ds = datasets.get(i);
            for (int j = 0; j < algs.size(); j++) {
                SortAlgorithm alg = algs.get(j);
                GenericList<Integer> copy = ds.getDataCopy();
                long start = System.nanoTime();
                alg.sort(copy);
                long elapsed = (System.nanoTime() - start) / 1_000_000;
                System.out.printf(
                        "%s | %s | %s | %d ➔ %d ms%n",
                        alg.getName(), ds.getName(), ds.getType(), ds.size(), elapsed
                );
                results.add(new Result(
                        alg.getName(), ds.getName(), ds.getType(), ds.size(), elapsed
                ));
            }
        }

        ReportGenerator.generateCsvReport(results, reportFile);
    }
}
