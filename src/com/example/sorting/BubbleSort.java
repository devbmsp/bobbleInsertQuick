package com.example.sorting;

public class BubbleSort implements SortAlgorithm {
    @Override
    public void sort(GenericList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j,     list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
