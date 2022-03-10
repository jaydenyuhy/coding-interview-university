package sort;

public abstract class ISort {

    /**
     * @return arithmetic name
     */
    abstract String name();

    /**
     * order by asc
     * @param array
     */
    abstract void sort(int[] array);

    void swapValues(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

}
