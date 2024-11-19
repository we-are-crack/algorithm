import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>(Comparator.reverseOrder());
        asc.offer(Integer.parseInt(br.readLine()));
        answer.append(asc.peek()).append("\n");
        while (n-- > 1) {
            int num = Integer.parseInt(br.readLine());

            if (num < asc.peek()) {
                desc.offer(num);
            } else {
                asc.offer(num);
            }

            redistribute(asc, desc);
            answer.append(asc.peek()).append("\n");
        }

        System.out.print(answer);
    }

    private static void redistribute(PriorityQueue<Integer> asc, PriorityQueue<Integer> desc) {
        int l = desc.size(), r = asc.size() - 1;

        if (l > r) {
            asc.offer(desc.poll());
        } else if (l + 1 < r) {
            desc.offer(asc.poll());
        }
    }
}
