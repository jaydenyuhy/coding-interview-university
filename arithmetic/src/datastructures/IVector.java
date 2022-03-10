package datastructures;

public interface IVector<E> {

    int size();

    int capacity();

    boolean isEmpty();

    E at(int index);

    void push(E e);

    void insert(int index, E e);

    void prepend(E e);

    E pop();

    void delete(int index);

    void remove(E e);

    int find(E e);

    void resize(int newCapacity);
}
