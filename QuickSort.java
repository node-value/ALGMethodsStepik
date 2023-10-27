package ALGMethods;
public class QuickSort {
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(int[] A, int l, int r) {
        int x = A[r], i = l - 1;
        for (int j = l; j < r; j++)
            if (A[j] <= x)
                swap(A, ++i, j);
        swap(A, ++i, r);
        return i;
    }

    public void quickSort(int[] A, int l, int r) {
        while (l < r) {
            int m = partition(A, l, r);
            quickSort(A, l, m - 1);
            l = m + 1;
        }
    }
}
