package ALGMethods;
import java.util.Arrays;
import java.util.Scanner;

public class DotsAndSegments {
        
    private int binarySearchLeft(int[] left, int dot) {
        int l = 1, r = left.length;
        while (l <= r) {
            int m = (int) Math.floor((l + r) / 2);
            if (left[m-1] <= dot)  l = m + 1; 
            else r = m - 1; 
        }
        return l-2;
    }

    private int binarySearchRight(int[] right, int dot, int leftBorder) {
        int l = 1, r = leftBorder+1;
        while (l <= r) {
            int m = (int) Math.floor((l + r) / 2);
            if (right[m-1]  < dot) l = m + 1;
            else r = m - 1;
        }
        return l-1;
    } 

    private int countDotInSegment(int[] left, int[] right, int dot) {
        int l = binarySearchLeft(left, dot);
        if (l == -1) return 0;
        int r = binarySearchRight(right, dot, l);
        return r > l ? 0 : l-r+1;
    }

    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int[] left = new int[scnr.nextInt()], right = new int[left.length];
            int nDots = scnr.nextInt();
            for (int i = 0; i < left.length; i++) {
                left[i] = scnr.nextInt();
                right[i] = scnr.nextInt();
            }
            Arrays.sort(left);
            Arrays.sort(right);
            for (int i = 0; i < nDots; i++)
                System.out.print(countDotInSegment(left, right, scnr.nextInt()) + " ");
        }
    }

    public static void main(String[] args) {
        new DotsAndSegments().run();
        
    }
}
/* 

*/