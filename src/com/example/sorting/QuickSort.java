package com.example.sorting;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] a, int low, int high) {
        if (low < high) {
            // pivô aleatório
            int pi = ThreadLocalRandom.current().nextInt(low, high + 1);
            swap(a, pi, high);
            int pivot = a[high];

            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (a[j] <= pivot) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, high);
            quickSort(a, low, i);
            quickSort(a, i + 2, high);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i]    = a[j];
        a[j]    = tmp;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}
