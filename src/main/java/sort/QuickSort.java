package sort;

public class QuickSort {

    public static void quickSort(int[] array, int startIndex, int endIndex, BreadCrumbs breadCrumbs) {
        breadCrumbs.endSubPath();
        if (array.length == 0)
            return;

        if (startIndex >= endIndex)
            return;
        if (startIndex < 0 || endIndex < 0)
            return;

        int middle = startIndex + (endIndex - startIndex) / 2;
        int pivot = array[middle];

        int i = startIndex;
        int j = endIndex;
        while (i <= j) {
            while (array[i] < pivot) {
                breadCrumbs.addPathValue(array[i]);
                i++;
            }

            while (array[j] > pivot) {
                breadCrumbs.addPathValue(array[j]);
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
            quickSort(array, startIndex, j, breadCrumbs);

        if (endIndex > i)
            quickSort(array, i, endIndex, breadCrumbs);
    }
}
