import array_max_value.ArrayMaxValue;
import binary_search.BinarySearch;
import bubble_sort.BubbleSort;
import junit.framework.TestCase;
import merge_sort.MergeSort;
import quick_sort.QuickSort;
import rabin_karp.RabinKarpExtended;

import java.util.ArrayList;
import java.util.Arrays;


public class Test extends TestCase {
    int[] values = {123, 10, 77, 45, 89, 456, 673, 65, 23};
    int[] sortedValues = {10, 23, 45, 65, 77, 89, 123, 456, 673};
    ArrayList<String> nameList = new ArrayList(Arrays.asList("Oleg", "Petr", "Katya", "Ivan", "Fedor", "Elena"));

    // tests array_max_value package
    public void testFindMaxValue() {
        assertEquals(673, ArrayMaxValue.getMaxValue(values));
    }

    // tests binary_search package
    public void testFindExistingElement() {
        assertEquals(0, new BinarySearch(nameList).search("Иван"));
    }

    public void testNoMatchesFind() {
        assertEquals(-1, new BinarySearch(nameList).search("Яков"));
    }

    // tests bubble_sort package
    public void testBubbleSort() {
        int[] unsortedArray = Arrays.copyOf(values, values.length);
        BubbleSort.sort(unsortedArray);
        assertTrue("Arrays aren`t equals", Arrays.equals(sortedValues, unsortedArray));
    }

    // tests quick_sort package
    public void testQuickSort() {
        int[] unsortedArray = Arrays.copyOf(values, values.length);
        QuickSort.sort(unsortedArray);
        assertTrue("Arrays aren`t equals", Arrays.equals(sortedValues, unsortedArray));
    }

    // tests merge_sort package
    public void testMergeSort() {
        int[] unsortedArray = Arrays.copyOf(values, values.length);
        MergeSort.mergeSort(unsortedArray);
        assertTrue("Arrays aren`t equals", Arrays.equals(sortedValues, unsortedArray));
    }

    // tests rabin_karp package
    public void testSearchFragmentByRK() {
        var obj = new RabinKarpExtended("AABAACAADAABAABA");
        var actual = obj.search("AABA");
        var expected = Arrays.asList(0, 9, 12);
        assertTrue("Lists aren`t equals", expected.equals(actual));
    }
}