package sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sort.BreadCrumbs;
import sort.QuickSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {
    private BreadCrumbs breadCrumbs;
    @BeforeEach
    public void setUp() {
        breadCrumbs = new BreadCrumbs();
    }
    @Test
    public void quickSortWithValidValues() {
        String expected = "/20_36_27_35_26_5_31_18_14_28_44/20_44_27_26_5/18_29_26_27/14/29/20/28/44_36_31/44/44/";
        int[] array = {20, 36, 27, 35, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40};
        QuickSort.quickSort(array, 0, 14, breadCrumbs);
        assertEquals(expected, breadCrumbs.getPath().toString());
    }
    @Test
    public void quickSortWithEmptyArray() {
        int[] array = {};
        String expected = "/";
        QuickSort.quickSort(array, 0, 14, breadCrumbs);
        assertEquals(expected, breadCrumbs.getPath().toString());
    }
    @Test
    public void quickSortWithNegativeStartIndex() {
        int[] array = {20, 36, 27, 35, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40};
        String expected = "/";
        QuickSort.quickSort(array, -10, 14, breadCrumbs);
        assertEquals(expected, breadCrumbs.getPath().toString());
    }

    @Test
    public void quickSortWithStartIndexMoreEndIndex() {
        int[] array = {20, 36, 27, 35, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40, 26, 5, 31, 48, 18, 49, 14, 28, 44, 29, 40};
        String expected = "/";

        QuickSort.quickSort(array, 20, 5, breadCrumbs);
        assertEquals(expected, breadCrumbs.getPath().toString());
    }
}
