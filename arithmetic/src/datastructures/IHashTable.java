package datastructures;

public interface IHashTable<K, V> {

    int hashToIndex(K k, int capacity);

    V put(K k, V v);

    boolean exist(K k);

    V get(K k);

    V remove(K k);
}
