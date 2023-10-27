package ALGMethods;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Stairs {

    private int getMinInd(int[][] arr, int i, int j, int k) {
        int min = Stream.of(arr[i][1], arr[j][1], arr[k][1]).min(Integer::compare).get();
        return min == arr[i][1] ? i : min == arr[j][1] ? j : k;
    }
    
    private int getMinInd(int[][] arr, int i, int j) {
        return Math.min(arr[i][1], arr[j][1]) == arr[i][1] ? i : j;
    }

    private int[][] build(int n) {
        int[][] D = new int[n + 1][2];
        for (int i = 1; i < D.length; i++) {
            D[i][0] = i % 6 == 0 ? getMinInd(D, i / 3, i / 2, i - 1)
                    : i % 3 == 0 ? getMinInd(D, i / 3, i - 1)
                    : i % 2 == 0 ? getMinInd(D, i / 2, i - 1) : i - 1;
            D[i][1] = D[D[i][0]][1]+1;
        }
        return D;
    }

    private List<Integer> getSeq(int[][] D, int n) {
        LinkedList<Integer> seq = new LinkedList<>();
        while (n != 1) seq.addFirst(n = D[n][0]);
        return seq;
    }

    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int n = scnr.nextInt();
            List<Integer> result = getSeq(build(n), n);
            System.out.println(result.size()-1);
            result.forEach(x -> System.out.print(x + " "));
            System.out.println(n);
        }
  
    }

    public static void main(String[] args) {
        new Stairs().run();
    }  
}

