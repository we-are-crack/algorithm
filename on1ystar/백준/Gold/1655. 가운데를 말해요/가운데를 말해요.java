import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        maxHeap.add(Integer.parseInt(br.readLine()));
        sb.append(maxHeap.peek()).append("\n");

        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (maxHeap.size() == minHeap.size()) {
                if (maxHeap.peek() < num && minHeap.peek() < num) {
                    maxHeap.add(minHeap.remove());
                    minHeap.add(num);
                } else {
                    maxHeap.add(num);
                }
            } else {
                if (maxHeap.peek() > num) {
                    minHeap.add(maxHeap.remove());
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.print(sb);
    }
}