package ALGMethods;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;

public class Stepik {
    
    // ** Terms
    private List<Integer> differentTerms(Integer n) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 1; n > 0; i++) {
            if (n - i < 0)
                result.set(result.size() - 1, result.getLast() + n);
            else
                result.add(i);
            n -= i;
        }
        return result;
    }
    // **

    // ** Knapsak
    private double knapsak() {
        try (Scanner scnr = new Scanner(System.in)) {
            double[][] items = new double[scnr.nextInt()][];
            int capacity = scnr.nextInt();
            for (int i = 0; i < items.length; i++)
                items[i] = new double[] { scnr.nextInt(), scnr.nextInt() };

            Arrays.sort(items, (x, y) -> (-1) * Double.compare(x[0] / x[1], y[0] / y[1]));

            double totalPrice = 0;
            for (int i = 0; i < items.length && capacity > 0; i++) {
                double quotient = capacity / items[i][1];
                totalPrice += items[i][0] * (quotient > 1 ? 1 : quotient);
                capacity -= items[i][1];
            }
            return totalPrice;
        }
    }
    // **

    // ** Dots and segments task
    private Long[][] readSegments() {
        try (Scanner scanner = new Scanner(System.in)) {
            Long[][] result = new Long[scanner.nextInt()][];
            for (int i = 0; i < result.length; i++)
                result[i] = new Long[] { scanner.nextLong(), scanner.nextLong() };

            return result;
        }
    }

    private List<Long> coverSegments(Long[][] segments) {
        Arrays.sort(segments, (x, y) -> Long.compare(x[1], y[1]));
        ArrayList<Long> result = new ArrayList<>();
        result.add(segments[0][1]);
        for (int i = 1; i < segments.length; i++)
            if (segments[i][0] > result.get(result.size() - 1))
                result.add(segments[i][1]);

        return result;
    }

    private void printList(List<? extends Object> list) {
        list.forEach(x -> System.out.println(x + " "));
        System.out.println();
    }
    // **

    // ** fibonacci task
    private BigInteger fib(int n) {
        BigInteger a = BigInteger.ZERO, b = BigInteger.ONE, c;
        for (int i = n; i > 0; i--) {
            c = a;
            a = b;
            b = b.add(c);
        }
        return a;
    }

    // **

    // ** GCD task
    private long gcd(long a, long b) {
        while (true) {
            if (a == 0 || b == 0) return Math.max(a, b);
            if (a >= b) a %= b;
            else b %= a;
        }
    }
}
