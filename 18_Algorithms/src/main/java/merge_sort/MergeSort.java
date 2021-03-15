package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        int n = array.length;
        if(n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        int lengthLeft = left.length;
        int lengthRight = right.length;
        int i = 0, j = 0, k = 0;
        while (i < lengthLeft && j < lengthRight) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < lengthLeft) {
            array[k++] = left[i++];
        }
        while (j < lengthRight) {
            array[k++] = right[j++];
        }
    }
}
