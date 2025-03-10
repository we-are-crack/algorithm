import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        MinHeap minHeap = new MinHeap();

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());

            if (v == 0) {
                if (minHeap.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(minHeap.remove());
                }
                sb.append("\n");
            } else {
                minHeap.add(v);
            }
        }

        System.out.print(sb);
    }

    /**
     * 0 -> root
     * v index * 2 + 1 = left child index
     * v index * 2 + 2 = right child index
     * (v index - 1) / 2 = parent index
     */
    static class MinHeap {
        private final List<Integer> dataList = new ArrayList<>();

        public void add(int v) {
            dataList.add(v);
            if (dataList.size() > 1) {
                upHeap();
            }
        }

        public int remove() {
            int root = removeRoot();
            if (dataList.size() > 1) {
                downHeap();
            }
            return root;
        }

        public boolean isEmpty() {
            return dataList.isEmpty();
        }

        private int removeRoot() {
            swap(0, dataList.size() - 1);
            return dataList.remove(dataList.size() - 1);
        }

        private void upHeap() {
            int target = dataList.size() - 1;
            int parent = (target - 1) / 2;

            while (dataList.get(target) < dataList.get(parent)) {
                swap(target, parent);
                target = parent;

                if (target != 0) {
                    parent = (target - 1) / 2;
                }
            }
        }

        private void downHeap() {
            int target = 0;

            while (true) {
                int leftChild = getLeftChild(target);
                int rightChild = getRightChild(target);

                if (leftChild != -1 && rightChild == -1 && dataList.get(target) > dataList.get(leftChild)) {
                    swap(target, leftChild);
                    target = leftChild;
                } else if (rightChild != -1) {
                    int minChild = dataList.get(leftChild) >= dataList.get(rightChild) ? rightChild : leftChild;

                    if (dataList.get(target) > dataList.get(minChild)) {
                        swap(target, minChild);
                        target = minChild;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        private int getLeftChild(int parent) {
            if (dataList.size() > parent * 2 + 1) {
                return parent * 2 + 1;
            }

            return -1;
        }

        private int getRightChild(int parent) {
            if (dataList.size() > parent * 2 + 2) {
                return parent * 2 + 2;
            }

            return -1;
        }

        private void swap(int a, int b) {
            int temp = dataList.get(a);
            dataList.set(a, dataList.get(b));
            dataList.set(b, temp);
        }
    }
}