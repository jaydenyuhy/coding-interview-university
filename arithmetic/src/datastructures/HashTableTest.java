package datastructures;

public class HashTableTest {

    public static void main(String[] args) {

        CustomHashTable<String, String> hashTable = new CustomHashTable<>();
        hashTable.put("k1", "v1");
        hashTable.put("k2", "v2");
        hashTable.put("k4", "v4");
        hashTable.put("k8", "v8");
        hashTable.put("k16", "v16");
        hashTable.put("k32", "v32");
        hashTable.put("k64", "v64");
        hashTable.put("k128", "v128");

        System.out.println("get k1 " + hashTable.get("k1"));
        System.out.println("get k2 " + hashTable.get("k2"));
        System.out.println("get k4 " + hashTable.get("k4"));
        System.out.println("get k8 " + hashTable.get("k8"));
        System.out.println("get k10 " + hashTable.get("k10"));

        System.out.println("exist v1 : " + hashTable.exist("k1"));
        hashTable.put("k1", "v11");
        System.out.println("get k1 " + hashTable.get("k1"));

        hashTable.remove("k1");

        System.out.println("exist v1 : " + hashTable.exist("k1"));
        System.out.println("get k1 " + hashTable.get("k1"));

    }
}
