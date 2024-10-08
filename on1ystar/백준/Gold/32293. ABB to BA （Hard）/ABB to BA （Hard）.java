import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t ; i++) {
            int n = Integer.parseInt(br.readLine());
            Deque<Character> lastDeque = new ArrayDeque<>();

            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                lastDeque.offerLast(s.charAt(j));
            }

            sb.append(scan(new ArrayDeque<>(), null, lastDeque));
            sb.append("\n");

        }

        System.out.print(sb);
    }
    private static StringBuilder scan(Deque<Character> frontDeque, Character middle, Deque<Character> lastDeque) {
        while (!lastDeque.isEmpty()) {
            if (Objects.isNull(middle)) {
                middle = lastDeque.pollFirst();
                continue;
            }

            if (frontDeque.isEmpty()) {
                frontDeque.offerLast(middle);
                middle = lastDeque.pollFirst();
                continue;
            }

            if (frontDeque.peekLast() == 'A' && middle == 'B' && lastDeque.peekFirst() == 'B') {
                lastDeque.pollFirst();
                lastDeque.offerFirst(frontDeque.pollLast());
                lastDeque.offerFirst(middle);

                if (frontDeque.isEmpty()) {
                    middle = null;
                } else {
                    middle = frontDeque.pollLast();
                }
            } else {
                frontDeque.offerLast(middle);
                middle = lastDeque.pollFirst();
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!frontDeque.isEmpty()) {
            sb.append(frontDeque.pollFirst());
        }

        if (!Objects.isNull(middle)) {
            sb.append(middle);
        }

        return sb;
    }
}