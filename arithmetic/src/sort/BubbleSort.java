package sort;

public class BubbleSort extends ISort{

    @Override
    public String name() {
        return BubbleSort.class.getSimpleName();
    }

    @Override
    public void sort(int[] array) {
        for(int i = 0; i < array.length; i++){
            for(int j = i + 1; j < array.length; j++){
                if(array[i] > array[j]){
                    swapValues(array, i, j);
                }
            }
        }
    }
}
