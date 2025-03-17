import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());
            graph[com1][com2] = true;
            graph[com2][com1] = true;
        }

        int answer = 0;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int computer = q.removeFirst();

            for (int i = 1; i < graph.length; i++) {
                if (graph[computer][i] && !visited[i]) {
                    visited[i] = true;
                    q.addLast(i);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}