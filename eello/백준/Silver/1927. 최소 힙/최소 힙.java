import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        MinHeap heap = new MinHeap();
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                answer.append(heap.poll()).append("\n");
            } else {
                heap.add(x);
            }
        }

        System.out.println(answer);
    }

    private static class MinHeap {

        private int[] arr = new int[100_000];
        private int size;

        public void add(int value) {
            arr[++size] = value;
            addRebuild(size);
        }

        public int poll() {
            if (isEmpty()) {
                return 0;
            }

            int min = arr[1];
            arr[1] = arr[size--];
            pollRebuild(1);

            return min;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void addRebuild(int index) {
            int parent = getParentIndex(index);

            if (index <= 1 || arr[parent] <= arr[index]) {
                return;
            }

            swap(index, parent);
            addRebuild(parent);
        }

        private void pollRebuild(int index) {
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);

            if (size < left) {
                return;
            }

            int target = left;
            if (right <= size && arr[right] < arr[left]) {
                target = right;
            }

            if (arr[index] < arr[target]) {
                return;
            }

            swap(index, target);
            pollRebuild(target);
        }

        private void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private int getParentIndex(int index) {
            return index / 2;
        }

        private int getLeftChildIndex(int index) {
            return 2 * index;
        }

        private int getRightChildIndex(int index) {
            return 2 * index + 1;
        }
    }
}
