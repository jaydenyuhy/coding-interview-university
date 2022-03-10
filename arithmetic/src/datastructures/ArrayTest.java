package datastructures;

public class ArrayTest {

    public static void main(String[] args) {


        CustomArrays<Integer> arrays = new CustomArrays<>();

        for(int i = 0; i < 20; i++){
            arrays.push(i + 10);
        }

        System.out.println("arrays size: " + arrays.size());
        System.out.println("arrays capacity: " + arrays.capacity());
        System.out.println("arrays isEmpty: " + arrays.isEmpty());
        System.out.println("arrays at[5] is: " + arrays.at(5));
        arrays.push(100);
        System.out.println("arrays push 100, then pop: " + arrays.pop());
        arrays.insert(0, 100);
        System.out.println("arrays insert 100 at index 0, at[0] is: " + arrays.at(0));
        arrays.prepend(99);
        System.out.println("arrays insert 99 by prepend, at[0] is: " + arrays.at(0));
        arrays.delete(0);
        System.out.println("arrays delete 0 at index 0, at[0] is: " + arrays.at(0));
        System.out.println("arrays find 10 index is: " + arrays.find(10));
        arrays.remove(10);
        System.out.println("arrays remove 10, then find 10 index is: " + arrays.find(10));
        System.out.println("arrays current capacity: " + arrays.capacity());
        System.out.println("arrays remove data until size == 5: ");
        while(arrays.size() > 5){
            arrays.delete(0);
        }
        System.out.println("arrays current capacity: " + arrays.capacity());
        for(int i = 0; i < arrays.size(); ++i){
            System.out.println("arrays index at " + i + " is: " + arrays.at(i));
        }
    }
}
