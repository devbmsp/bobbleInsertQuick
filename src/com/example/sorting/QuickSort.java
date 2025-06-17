
package com.example.sorting;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(GenericList<Integer> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(GenericList<Integer> a, int low, int high) {
        if (low < high) {
            int pi = ThreadLocalRandom.current().nextInt(low, high + 1);
            swap(a, pi, high);
            int pivot = a.get(high);

            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (a.get(j) <= pivot) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, high);

            quickSort(a, low, i);
            quickSort(a, i + 2, high);
        }
    }

    private void swap(GenericList<Integer> a, int i, int j) {
        int tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}
