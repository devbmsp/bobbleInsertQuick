package com.example.sorting;

public class GenericList<T> {
    private Object[] elements;
    private int size;

    public GenericList() {
        this.elements = new Object[10];
        this.size     = 0;
    }

    public void add(T item) {
        if (size == elements.length) {
            Object[] tmp = new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                tmp[i] = elements[i];
            }
            elements = tmp;
        }
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return (T) elements[index];
    }

    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        elements[index] = item;
    }

    public int size() {
        return size;
    }
}
