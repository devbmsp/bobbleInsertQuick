package com.example.sorting;

/** Guarda uma medição: algoritmo, dataset, tipo, tamanho e tempo em ms */
public class Result {
    private final String            algorithm;
    private final String            dataSetName;
    private final DataSet.Type      type;
    private final int               size;
    private final long              timeMs;

    public Result(String algorithm, String dataSetName, DataSet.Type type, int size, long timeMs) {
        this.algorithm   = algorithm;
        this.dataSetName = dataSetName;
        this.type        = type;
        this.size        = size;
        this.timeMs      = timeMs;
    }

    @Override
    public String toString() {
        return algorithm + "," +
                dataSetName + "," +
                type.name()   + "," +
                size          + "," +
                timeMs;
    }
}
