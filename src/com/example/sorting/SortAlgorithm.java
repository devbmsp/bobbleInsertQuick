package com.example.sorting;

/**
 * Contrato para qualquer algoritmo de ordenação:
 * recebe uma GenericList<Integer> e a ordena in-place.
 */
public interface SortAlgorithm {
    /**
     * Ordena a lista inteira.
     * @param list lista de inteiros a ser ordenada
     */
    void sort(GenericList<Integer> list);

    /** Nome do algoritmo */
    String getName();
}
