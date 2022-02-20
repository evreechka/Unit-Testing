package sort;

public class QuickSort {

    public static void quickSort(int[] array, int startIndex, int endIndex) {
        if (array.length == 0)
            return;

        if (startIndex >= endIndex)
            return;

        int middle = startIndex + (endIndex - startIndex) / 2;
        int pivot = array[middle];

        int i = startIndex;
        int j = endIndex;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (startIndex < j)
            quickSort(array, startIndex, j);

        if (endIndex > i)
            quickSort(array, i, endIndex);
    }
}
