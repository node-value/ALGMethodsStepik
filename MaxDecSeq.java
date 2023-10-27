package ALGMethods;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;



class MaxDecSeq {
    
    private int binarySearchRight(List<Integer> right, int dot) {
        int l = 1, r = right.size();
        while (l <= r) {
            int m = (int) Math.floor((l + r) / 2);
            if (right.get(m - 1) <= dot) l = m + 1;
            else                         r = m - 1;
        }
        return l - 2;
    }

    private int[] LISBottomUp(int[] arr) {
        int[] D = new int[arr.length];
        ArrayList<Integer> seq = new ArrayList<>(100);
        for (int i = 0; i < arr.length; i++) {
            int lessInd = binarySearchRight(seq, arr[i]);

            if (lessInd+1 > seq.size()-1) seq.add(arr[i]);
            else seq.set(lessInd+1, arr[i]);

            D[i] = lessInd+2;
        }
        return D;
    }

    private int getLastMaxInd(int[] D) {
        int i = 0;
        for (int j = 1; j < D.length; j++) if (D[j] >= D[i]) i = j;
        return i;
    }
    
    private List<Integer> getIndexes(int[] arr, int[] D) {
        int prev = getLastMaxInd(D);
        LinkedList<Integer> result = new LinkedList<>();
        result.add(arr.length - prev);
        for (int i = prev - 1; i >= 0; i--)
            if (D[i] + 1 == D[prev] && arr[i] <= arr[prev])
                result.add(arr.length - (prev = i));
        return result;
    }

    private void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int[] arr = new int[Integer.parseInt(in.readLine())];
            String[] tokens = in.readLine().split(" ");
            for (int i = arr.length - 1; i >= 0; i--)
                arr[i] = Integer.parseInt(tokens[arr.length-1-i]);

            List<Integer> res = getIndexes(arr, LISBottomUp(arr));
            System.out.println(res.size());
            StringBuilder bld = new StringBuilder();
            for (Integer i : res) bld.append(i).append(" ");
            System.out.println(bld.toString());
        } catch (Exception ignored) {}

    }

    public static void main(String[] args) {
        new MaxDecSeq().run();
    }   
}