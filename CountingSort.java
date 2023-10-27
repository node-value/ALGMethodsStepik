package ALGMethods;
import java.util.Arrays;
import java.util.Scanner;

class CountingSort {

    public int[] countSort(int[] arr) {
        int maxVal = Arrays.stream(arr).max().getAsInt();
        int[] appearance = new int[maxVal], result = new int[arr.length];
        for (int j = 0; j < arr.length;    j++) appearance[arr[j]]++;
        for (int i = 2; i < maxVal;        i++) appearance[i] += appearance[i-1];
        for (int j = arr.length-1; j >= 0; j--) result[--appearance[arr[j]]] = arr[j];
        return result;
    }
    
    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int[] arr = new int[scnr.nextInt()];
            for (int i = 0; i < arr.length; i++) arr[i] = scnr.nextInt();
            for (int x : countSort(arr)) System.out.print(x + " ");
        }   
    }

    public static void main(String[] args) {
        new CountingSort().run();
    }
}
