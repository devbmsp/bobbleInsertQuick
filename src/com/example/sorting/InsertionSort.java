package com.example.sorting;

public class InsertionSort implements SortAlgorithm {
    @Override
    public void sort(GenericList<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j   = i - 1;
            // desloca tudo que for maior que key para a direita
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            // insere a chave no lugar correto
            list.set(j + 1, key);
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
