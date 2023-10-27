package ALGMethods;
import java.util.Scanner;

class BinarySearch {

    private int binarySearch(int[] arr, int k) {
        int l = 1, r = arr.length;
        while (l <= r) {
            int m = (int) Math.floor((l+r)/2);
            if (arr[m-1] == k) return m;
            else if (arr[m-1] > k) r = m - 1;
            else l = m + 1;
        }
        return -1;
    }

    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int[] arr = new int[scnr.nextInt()];
            for (int i = 0; i < arr.length; i++) 
                arr[i] = scnr.nextInt();
            int n = scnr.nextInt();
            for (int i = 0; i < n; i++) 
                System.out.print(binarySearch(arr, scnr.nextInt()) + " ");
        }
    }

    public static void main(String[] args) {
        new BinarySearch().run();
    }
}
