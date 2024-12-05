import java.io.*;
import java.util.*;

public class Main {

    private static List<int[]>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[s].add(new int[]{e, t});
        }

        int[] x2Home = pointToAll(n, x);
        int[] home2X = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == x) {
                continue;
            }

            home2X[i] = pointToPoint(n, i, x);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, home2X[i] + x2Home[i]);
        }

        System.out.println(answer);
    }

    private static int[] pointToAll(int n, int start) {
        return dijkstra(n, start);
    }

    private static int pointToPoint(int n, int start, int end) {
        return dijkstra(n, start)[end];
    }

    private static int[] dijkstra(int n, int start) {
        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));;
        queue.offer(new int[]{start, 0});
        cost[start] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cost[cur[0]] < cur[1]) {
                continue;
            }

            for (int[] next : adj[cur[0]]) {
                if (cur[1] + next[1] < cost[next[0]]) {
                    cost[next[0]] = cur[1] + next[1];
                    queue.offer(new int[]{next[0], cost[next[0]]});
                }
            }
        }

        return cost;
    }
}
