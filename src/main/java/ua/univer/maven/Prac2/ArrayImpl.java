package ua.univer.maven.Prac2;

import java.util.Iterator;


public class ArrayImpl implements Array {
    Object[] array;

    public ArrayImpl(int size) {
        if (size > 0) {
            this.size = size;
            this.array = new Object[size];
        } else if (size == 0) {
            this.array = null;
        } else {
            throw new IllegalArgumentException("Illegal size of the array : " + size);
        }
    }


    int size = array.length;

    @Override
    public void clear() {
        for (Object current : this.array) {
            current = null;
        }
    }

    @Override
    public int size() {
        return this.array.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {


        int currentSize = array.length;
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < currentSize && array[currentIndex] != null;
        }

        @Override
        public Object next() {
            return array[currentIndex++];
        }

    }

    @Override
    public void add(Object element) {
        Object[] temp = array;
        array = new Object[temp.length + 1];
        array = temp.clone();
        array[array.length - 1] = element;
    }

    @Override
    public void set(int index, Object element) {
        array[index] = element;
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        int numMoved = this.array.length - index - 1;
        if (numMoved > 0) {
            System.arraycopy(get(index), index + 1, get(index), index, numMoved);
        }
        array[--size] = null;
    }

    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        if (!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            Object object = it.next();
            sb.append(object == this ? "(this Array)" : object);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        Object[] array = {"A", "B", "C"};
        System.out.println(array.toString());
    }

}
