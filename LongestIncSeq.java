package ALGMethods;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestIncSeq {

    private int LISBottomUp(int[] arr) {
        ArrayList<Integer> D = new ArrayList<>(Stream.generate(() -> 1).limit(arr.length).collect(Collectors.toList()));
        for (int i = 0; i < arr.length; i++) 
            for (int j = 0; j < i; j++)
                if (arr[i] >= arr[j] && arr[i] % arr[j] == 0 && D.get(j)+1 > D.get(i))
                    D.set(i, D.get(j)+1);
        return D.stream().max(Integer::compare).get();
    }
    
    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int[] arr = new int[scnr.nextInt()];
            for (int i = 0; i < arr.length; i++) arr[i] = scnr.nextInt();
            System.out.println(LISBottomUp(arr));    
        }
    }

    public static void main(String[] args) {
        new LongestIncSeq().run();
    }
    
}
