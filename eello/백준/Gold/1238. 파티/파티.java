import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<int[]>[] adj = new List[n + 1];
        List<int[]>[] reverseAdj = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[s].add(new int[]{e, t});
            reverseAdj[e].add(new int[]{s, t});
        }

        int[] home2X = dijkstra(adj, n, x);
        int[] x2Home = dijkstra(reverseAdj, n, x);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, home2X[i] + x2Home[i]);
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(List<int[]>[] adj, int n, int start) {
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
