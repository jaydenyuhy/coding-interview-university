package datastructures;

public class CustomHashTable<K, V> implements IHashTable<K, V>{

    private Entry<K, V>[] table;

    public CustomHashTable(){
        table = (Entry<K,V>[])new Entry[8];
    }

    static final int hash(Object obj){
        int h;
        return obj == null ? 0 : (h = obj.hashCode()) ^ (h >>> 16);
    }

    public int hashToIndex(K k){
        return hashToIndex(k, table.length);
    }

    @Override
    public int hashToIndex(K k, int capacity) {
        return hash(k) & (capacity - 1);
    }

    @Override
    public V put(K k, V v) {
        int index = hashToIndex(k, table.length);
        Entry<K, V> entry = table[index];
        if(entry == null){
            entry = new Entry<>(k, v, null);
            table[index] = entry;
            return null;
        }

        while(true){
            if(entry.key != null && (entry.key == k || entry.key.equals(k))){
                V oldValue = entry.value;
                entry.value = v;
                return oldValue;
            } else if(entry.next == null){
                entry.next = new Entry<>(k, v, null);
                break;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean exist(K k) {
        return get(k) != null;
    }

    @Override
    public V get(K k) {
        int index = hashToIndex(k, table.length);
        Entry<K, V> entry = table[index];
        while(entry != null){
            if(entry.key != null && (entry.key == k || entry.key.equals(k))){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V remove(K k) {
        int index = hashToIndex(k, table.length);
        Entry<K, V> entry = table[index];
        Entry<K, V> prev = null;
        while(entry != null){
            if(entry.key != null && (entry.key == k || entry.key.equals(k))){
                if(prev != null){
                    prev.next = entry.next;
                    table[index] = prev;
                } else {
                    table[index] = entry.next;
                }
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    static class Entry<K, V>{

        public K key;
        public V value;
        public Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
