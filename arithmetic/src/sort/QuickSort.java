package sort;

/**
 * 快速排序
 * 可以取数组第一个元素或者最后一个元素进行查找，两种的区别是查找基准(pivot)的下标时边界不一样
 * 如果要实现不同的基准实现，只要把最后想要的基准值和第一个/最后一个元素进行替换，就可以和普通的快排一样调用划分函数
 */
public class QuickSort extends ISort {

    @Override
    public String name() {
        return QuickSort.class.getSimpleName();
    }

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int startIndex, int endIndex) {
        if (endIndex - startIndex < 0) {
            return;
        }

        selectPivotMedianOfThree(array, startIndex, endIndex);
        int pivot = array[endIndex];
        int partition = partition(array, pivot, startIndex, endIndex);
        sort(array, startIndex, partition - 1);
        sort(array, partition + 1, endIndex);
    }

    /*函数作用：取待排序序列中low、mid、high三个位置上数据，选取他们中间的那个数据作为枢轴*/
    private void selectPivotMedianOfThree(int[] array, int low, int high) {
        int mid = low + ((high - low) >> 1);//计算数组中间的元素的下标

        //使用三数取中法选择枢轴

        //目标: arr[mid] >= arr[low]
        if (array[mid] < array[low]) {
            swapValues(array, mid, low);
        }
        //目标: array[high] >= array[low]
        if (array[high] < array[low]) {
            swapValues(array, high, low);
        }
        //目标: array[high] < array[mid]
        if (array[mid] > array[high]) {
            swapValues(array, mid, high);
        }
        //此时，array[low] <= array[high] <= array[mid]
        //high的位置上保存这三个位置中间的值
        //分割时可以直接使用high位置的元素作为枢轴，而不用改变分割函数了
    }

    private int partition(int[] array, int pivot, int left, int right) {

        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {
            while (array[++leftPointer] < pivot) ;
            while (rightPointer > 0 && array[--rightPointer] > pivot) ;
            if (leftPointer >= rightPointer) {
                break;
            } else {
                swapValues(array, leftPointer, rightPointer);
            }
        }

        //基准值替换
        swapValues(array, leftPointer, right);
        return leftPointer;
    }

    //用数组第一个元素时的实现方法替换
    private void sortByStartIndex(int[] array, int startIndex, int endIndex) {
        if (endIndex - startIndex < 0) {
            return;
        }

        int pivot = array[startIndex];
        int partition = partitionByStartIndex(array, pivot, startIndex, endIndex);
        sort(array, startIndex, partition - 1);
        sort(array, partition + 1, endIndex);
    }

    private int partitionByStartIndex(int[] array, int pivot, int left, int right) {

        int leftPointer = left;
        int rightPointer = right + 1;

        while (true) {
            while (leftPointer < right && array[++leftPointer] < pivot) ;
            while (rightPointer > 0 && array[--rightPointer] > pivot) ;
            if (leftPointer >= rightPointer) {
                break;
            } else {
                swapValues(array, leftPointer, rightPointer);
            }
        }

        swapValues(array, rightPointer, left);
        return rightPointer;
    }
}
