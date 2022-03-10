package datastructures;

import java.util.Arrays;

public class CustomArrays<E> implements IVector<E>{

    private Object[] elementData;
    private int elementCount;

    public CustomArrays(int initialCapacity){
        this.elementData = new Object[initialCapacity];
    }

    public CustomArrays(){
        this(16);
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public int capacity() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E at(int index) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        return (E) elementData[index];
    }

    @Override
    public void push(E e) {
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
    }

    @Override
    public void insert(int index, E e) {

        if (index > elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " > " +
                    elementCount);
        }
        else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        ensureCapacityHelper(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = e;
        elementCount++;
    }

    @Override
    public void prepend(E e) {
        insert(0, e);
    }

    @Override
    public E pop() {
        E e = at(elementCount - 1);
        delete(elementCount - 1);
        return e;
    }

    @Override
    public void delete(int index) {
        deleteElementAtIndex(index);
    }

    @Override
    public void remove(E e) {
        int index = find(e);
        if(index != -1){
            deleteElementAtIndex(index);
        }
    }

    private void deleteElementAtIndex(int index){
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        }
        else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
        if(elementCount <= elementData.length / 4){
            elementData = Arrays.copyOf(elementData, elementData.length / 2);
        }
    }

    @Override
    public int find(E e) {
        for(int i = 0; i < elementCount; i++){
            if(e == null && elementData[i] == null){
                return i;
            } else if(e != null && e.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void resize(int newCapacity) {
        ensureCapacityHelper(newCapacity);
    }

    private void ensureCapacityHelper(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int newCapacity = elementData.length;
        while (newCapacity < minCapacity){
            newCapacity = newCapacity * 2;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
}
