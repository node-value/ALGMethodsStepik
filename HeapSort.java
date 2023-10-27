package ALGMethods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort {

    private int swapChld(int index, int child, ArrayList<Integer> queue) {
        Collections.swap(queue, child, index);
        return child;
    }

    private int getMaxChldInd(int[] inds, ArrayList<Integer> queue) {
        if (inds[1] == -1) return inds[0];
        return queue.get(inds[0]) > queue.get(inds[1]) ? inds[0] : inds[1];
    }

    private int[] getChldsIndex(int index, int last, ArrayList<Integer> queue) {
        int left = 2*(index + 1)-1, right = 2*(index + 1), lastI = queue.size()-1;
        return left > lastI ? new int[] { -1, -1 } : right > lastI ? new int[] { left, -1 } : new int[] { left, right };
    }

    private boolean isChldValid(int[] chlds) { return chlds[0] != chlds[1]; }

    private void siftDown(ArrayList<Integer> arr, int l, int r) {
        if (!isChldValid(getChldsIndex(l, r, arr))) return;
        int chld = getMaxChldInd(getChldsIndex(l, r, arr), arr);
        if (arr.get(chld) < arr.get(l)) return;
        siftDown(arr,swapChld(l, chld, arr), r);
    }

    private void buildMaxHeap(ArrayList<Integer> arr) {
        for (int i = (int) Math.floor(arr.size()/2); i > 0; i--) siftDown(arr, i-1, arr.size()-1);
    }

    private void heapSort(ArrayList<Integer> arr) {
        buildMaxHeap(arr);
        int size = arr.size();
        for (int i = arr.size(); i > 1; i--) {
            Collections.swap(arr, (size--)-1, 0);
            siftDown(arr, 0, size-1);
        }
    }

    private void run() {
        ArrayList<Integer> list = new ArrayList<>(List.of(4, 5, 1, 3, 14, 1 ,9));
        heapSort(list);
        System.out.println(list.toString());
    }

    public static void main(String[] args) {
        new HeapSort().run();
    }   
    
}
