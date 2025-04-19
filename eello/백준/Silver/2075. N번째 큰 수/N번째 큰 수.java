import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int number = Integer.parseInt(st.nextToken());

                if (pq.size() < n) {
                    pq.add(number);
                } else if (pq.peek() < number) {
                    pq.poll();
                    pq.add(number);
                }
            }
        }

        System.out.print(pq.poll());
    }
}
