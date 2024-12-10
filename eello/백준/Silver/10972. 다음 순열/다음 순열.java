import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(seq[n - 1]);

        int i;
        for (i = n - 2; i >= 0; i--) {
            if (seq[i] > seq[i + 1]) {
                pq.offer(seq[i]);
            } else {
                int temp = seq[i];
                List<Integer> list = new ArrayList<>();
                while (!pq.isEmpty() && pq.peek() < temp) {
                    list.add(pq.poll());
                }

                seq[i] = pq.poll();
                pq.offer(temp);
                pq.addAll(list);

                break;
            }
        }

        StringBuilder answer = new StringBuilder();
        if (i == -1) {
            answer.append(-1);
        } else {
            for (int j = 0; j < n; j++) {
                if (j <= i) {
                    answer.append(seq[j]);
                } else {
                    answer.append(pq.poll());
                }
                answer.append(" ");
            }
        }

        System.out.println(answer);
    }
}
