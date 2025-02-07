import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (n-- > 0) {
            int input = Integer.parseInt(br.readLine());

            if (input > 0) {
                pq.offer(input);
            } else {
                answer.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
            }
        }

        System.out.print(answer);
    }
}
