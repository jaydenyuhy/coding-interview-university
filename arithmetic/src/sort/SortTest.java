package sort;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {

        int[] test1 = new int[]{3, 14, 2, 6, 9, 7, 4, 5, 2, 1, 20, 15};
        int[] test2 = new int[]{3, 1, 4, 2, 5};

        int[][] tests = new int[][]{test1, test2};

//        ISort sort = new QuickSort();
        ISort sort = new BubbleSort();

        System.out.println("Sort name: " + sort.name());
        for(int[] test : tests){
            sort.sort(test);
            System.out.println("Result: " + Arrays.toString(test));
        }

    }
}
