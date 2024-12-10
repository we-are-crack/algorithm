import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] graph;
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;
        travel(0, 1, 0, visited);

        System.out.println(minCost);
    }

    static void travel(int nowCity, int count, int cost, boolean[] visited) {
        if (count == n) {
            if (graph[nowCity][0] != 0) {
                minCost = Math.min(minCost, cost + graph[nowCity][0]);
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (graph[nowCity][i] == 0 || visited[i]) {
                continue;
            }

            visited[i] = true;
            travel(i, count + 1, cost + graph[nowCity][i], visited);
            visited[i] = false;
        }
    }
}