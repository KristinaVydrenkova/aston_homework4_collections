package vydrenkova.internship;

import java.util.Collection;
import java.util.Comparator;


/**
 * A custom implementation of a dynamic array list that can hold elements of type E,
 * which must implement the Comparable interface.
 *
 * @param <E> the type of elements held in this list, must implement Comparable
 */
public class MyArrayList<E extends Comparable<E>> {
    private static final int CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public MyArrayList() {
        elements = new Object[CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty list with an initial capacity.
     *
     * @param capacity capacity of list
     */
    public MyArrayList(int capacity) {
        elements = new Object[capacity];
        size = capacity;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    public boolean add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E element) {
        if (checkSize(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's iterator.
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addAll(Collection<? extends E> c) {
        ensureCapacity(size + c.size());
        for (E element : c) {
            elements[size++] = element;
        }
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        if (checkSize(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (checkSize(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    /**
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts the elements of this list into ascending order, according to the natural ordering
     * of its elements. All elements in this list must implement the Comparable interface.
     * Furthermore, all elements in this list must be mutually comparable (that is,
     * e1.compareTo(e2) must not throw a ClassCastException for any elements e1 and e2 in the list).
     */
    public void sort() {
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (((E) elements[j]).compareTo((E) elements[j + 1]) > 0) {
                    E temp = (E) elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts the elements of this list into ascending order, according to the order induced
     * by the specified comparator.
     *
     * @param comparator the comparator to determine the order of the elements.
     *                   A null value indicates that the elements' natural ordering should be used.
     */
    public void quickSort(Comparator<? super E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Returns a string representation of this list. The string representation consists of
     * a list of the elements enclosed in square brackets ("[]"). Adjacent elements are
     * separated by the characters ", " (comma and space).
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void quickSort(int low, int high, Comparator<? super E> comparator) {
        if (low < high) {
            int pi = partition(low, high, comparator);

            quickSort(low, pi - 1, comparator);
            quickSort(pi + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super E> comparator) {
        E pivot = (E) elements[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private boolean checkSize(int index) {
        return index < 0 || index >= size;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }


}
