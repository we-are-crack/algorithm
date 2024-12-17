import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] obj = new int[101];
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            obj[from] = to;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] mem = new int[101];
        Arrays.fill(mem, Integer.MAX_VALUE);

        queue.add(new int[]{1, 0});
        mem[1] = 0;

        int answer = 101;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (mem[cur[0]] < cur[1]) {
                continue;
            }

            if (cur[0] == 100) {
                answer = cur[1];
            }

            for (int i = 1; i <= 6; i++) {
                int next = cur[0] + i;
                if (100 < next) {
                    continue;
                }

                if (obj[next] != 0) {
                    next = obj[next];
                }

                if (cur[1] + 1 < mem[next]) {
                    mem[next] = cur[1] + 1;
                    queue.add(new int[]{next, mem[next]});
                }
            }
        }

        System.out.println(answer);
    }
}
