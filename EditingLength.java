package ALGMethods;
import java.util.Scanner;
import java.util.stream.Stream;

public class EditingLength {
    
    private int editDistBU(char[] a, char[] b) {

        int[][] D = new int[a.length+1][b.length+1];
        for (int i = 0; i <= a.length; i++) D[i][0] = i;
        for (int j = 0; j <= b.length; j++) D[0][j] = j;
        for (int i = 1; i <= a.length; i++) 
            for (int j = 1; j <= b.length; j++) 
                D[i][j] = Stream.of(D[i-1][j]+1, D[i][j-1]+1, D[i-1][j-1] + (a[i-1] == b[j-1] ? 0 : 1)).min(Integer::compare).get();
        return D[a.length][b.length];       
    }

    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            System.out.println(editDistBU(scnr.next().toCharArray(), scnr.next().toCharArray()));
        }
    }

    public static void main(String[] args) {
        new EditingLength().run();
    }
    
}
