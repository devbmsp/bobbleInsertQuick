// src/com/example/sorting/ReportGenerator.java
package com.example.sorting;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportGenerator {

    public static void generateCsvReport(GenericList<Result> results, String outputPath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
            pw.println("Algoritmo,DataSet,Tipo,Tamanho,TempoMs");
            for (int i = 0; i < results.size(); i++) {
                pw.println(results.get(i).toString());
            }
            System.out.println("Relatório gerado em: " + outputPath);
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
