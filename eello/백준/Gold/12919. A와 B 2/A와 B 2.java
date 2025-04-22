import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        String target = br.readLine();

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, target.length() - 1, 1});

        boolean answer = false;
        while (!answer && !que.isEmpty()) {
            int[] curr = que.poll();
            int s = curr[0], e = curr[1], d = curr[2];

            if (e - s + 1 == start.length()) {
                StringBuilder temp = new StringBuilder();
                for (int i = s; i <= e; i++) {
                    temp.append(target.charAt(i));
                }

                if (d < 0) {
                    temp = temp.reverse();
                }

                answer = start.contentEquals(temp);

                continue;
            }

            if (d > 0) {
                if (target.charAt(e) == 'A') {
                    que.add(new int[]{s, e - 1, d});
                }

                if (target.charAt(s) == 'B') {
                    que.add(new int[]{s + 1, e, d * -1});
                }
            } else {
                if (target.charAt(s) == 'A') {
                    que.add(new int[]{s + 1, e, d});
                }

                if (target.charAt(e) == 'B') {
                    que.add(new int[]{s, e - 1, d * -1});
                }
            }
        }

        System.out.print(answer ? 1 : 0);
    }
}
