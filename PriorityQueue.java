package ALGMethods;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class PriorityQueue {
    private ArrayList<Integer> queue;

    private PriorityQueue() {
        queue = new ArrayList<>();
    }

    private int getParentIndex(int index) {
        int result = (int) Math.floor((index+1)/2) - 1;
        return result < 0 ? 0 : result; 
    }

    private int swapParent(int index) {
        Collections.swap(queue, index, getParentIndex(index));
        return getParentIndex(index);
    }

    private int[] getChldsIndex(int index) {
        int left = 2*(index+1)-1, right = 2*(index+1), lastI = queue.size()-1;
        return left > lastI ? new int[]{-1,-1} : right > lastI ? new int[]{left,-1} : new int[] {left,right};
    }

    private int getMaxChldInd(int[] inds) {
        if (inds[1] == -1) return inds[0];
        return queue.get(inds[0]) > queue.get(inds[1]) ? inds[0] : inds[1];
    }

    private int swapChld(int index, int child) {
        Collections.swap(queue, child, index);
        return child;
    }

    private void swapHeadAndTail() {
        Collections.swap(queue, 0, queue.size()-1);
    }

    private void floatUp(int index) {
        if (queue.get(getParentIndex(index)) >= queue.get(index)) return;
        else floatUp(swapParent(index));
    }
    
    private boolean isChldValid(int[] chlds) { return chlds[0] != chlds[1]; }

    private void floatDown(int index) {
        if (!isChldValid(getChldsIndex(index))) return;
        int chld = getMaxChldInd(getChldsIndex(index));
        if (queue.get(chld) < queue.get(index)) return;
        floatDown(swapChld(index, chld));
    }

    private void insert(Integer val) {
        queue.add(val);
        floatUp(queue.size()-1);
    }

    private String extractMax() {
        if (queue.size() == 0) return "Queue is empty!";
        swapHeadAndTail();
        Integer maxVal = queue.remove(queue.size()-1);
        floatDown(0);
        return "" + maxVal;

    }

    private void run() {
        PriorityQueue queue = new PriorityQueue();
        try (Scanner scnr = new Scanner(System.in)) {
            int n = scnr.nextInt();
            for (int i = 0; i < n; i++) 
                if (scnr.next().equals("Insert")) queue.insert(scnr.nextInt());
                else System.out.println(queue.extractMax());   
        }
    }

    public static void main(String[] args) {
        new PriorityQueue().run();
    }
}
