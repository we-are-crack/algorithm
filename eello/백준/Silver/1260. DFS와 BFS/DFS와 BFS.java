import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new List[n + 1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(adj[i]);
        }

        StringBuilder answer = new StringBuilder();
        dfs(adj, v, answer, new BitSet());
        answer.append("\n");
        bfs(adj, v, answer);

        System.out.print(answer);
    }

    private static void dfs(List<Integer>[] adj, int curr, StringBuilder sb, BitSet visit) {
        visit.set(curr);
        sb.append(curr).append(" ");

        for (Integer next : adj[curr]) {
            if (visit.get(next)) {
                continue;
            }

            dfs(adj, next, sb, visit);
        }
    }

    private static void bfs(List<Integer>[] adj, int curr, StringBuilder sb) {
        Queue<Integer> que = new ArrayDeque<>();
        BitSet visit = new BitSet();

        que.add(curr);
        visit.set(curr);

        while (!que.isEmpty()) {
            int cur = que.poll();

            sb.append(cur).append(" ");

            for (Integer next : adj[cur]) {
                if (visit.get(next)) {
                    continue;
                }

                que.add(next);
                visit.set(next);
            }
        }
    }
}
