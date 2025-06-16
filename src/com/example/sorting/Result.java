package com.example.sorting;

public class Result {
    private final String            algorithm;
    private final String            dataSetName;
    private final DataSet.Type      type;
    private final int               size;
    private final long              timeMs;

    public Result(String alg, String ds, DataSet.Type t, int sz, long tm) {
        this.algorithm   = alg;
        this.dataSetName = ds;
        this.type        = t;
        this.size        = sz;
        this.timeMs      = tm;
    }

    @Override
    public String toString() {
        // formato CSV
        return algorithm + "," +
                dataSetName + "," +
                type.name() + "," +
                size + "," +
                timeMs;
    }
}
