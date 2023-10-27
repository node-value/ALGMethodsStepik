package ALGMethods;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.stream.Collectors;

class CountInversions {

    private long inversions = 0;

    private Integer[] merge(Integer[] up, Integer[] down) {
        Integer[] merged = new Integer[up.length + down.length];
        int m = 0, u = 0, d = 0;
        while (u < up.length && d < down.length)
            if (up[u] > down[d]) {
                inversions += (up.length - u);
                merged[m++] = down[d++];
            } else
                merged[m++] = up[u++];
        for (; m < merged.length; m++)
            merged[m] = u < up.length ? up[u++] : down[d++];
        return merged;
    }

    public static int findNextPowerOf2(int n) {
        n--;
        n |= n >> 1; n |= n >> 2; n |= n >> 4; n |= n >> 8; n |= n >> 16;
        return ++n;
    }

    private ArrayDeque<Integer[]> getQueueOfArrays(LinkedList<Integer> arr) {
        return new ArrayDeque<>(arr.stream().map(x -> new Integer[] { x }).collect(Collectors.toList()));
    }

    private long countInversions(LinkedList<Integer> arr) {
        ArrayDeque<Integer[]> queue = getQueueOfArrays(arr);
        while (queue.size() > 1)
            queue.addLast(merge(queue.pop(), queue.pop()));
        return inversions;
    }

    private void run() {
        try (Scanner scnr = new Scanner(System.in)) {
            int n = scnr.nextInt();
            int powerOf2 = findNextPowerOf2(n);
            LinkedList<Integer> arr = new LinkedList<>();
            for (int i = 0; i < powerOf2; i++)
                arr.add(i, i < powerOf2 - n ? 0 : scnr.nextInt());

            System.out.println(countInversions(arr));
        }
    }

    public static void main(String[] args) {
        new CountInversions().run();
    }
}