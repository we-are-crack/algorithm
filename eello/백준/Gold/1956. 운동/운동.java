import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = INF;
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] edge = new int[v + 1][v + 1];

        for (int i = 1; i <= v; i++) {
            Arrays.fill(edge[i], INF);
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge[a][b] = c;
        }

        for (int k = 1; k <= v; k++) {
            for (int f = 1; f <= v; f++) {
                for (int t = 1; t <= v; t++) {
                    if (edge[f][k] == INF || edge[k][t] == INF) {
                        continue;
                    }

                    edge[f][t] = Math.min(edge[f][t], edge[f][k] + edge[k][t]);
                    if (f == t) {
                        answer = Math.min(answer, edge[f][t]);
                    }
                }
            }
        }

        System.out.println(answer != INF ? answer : -1);
    }
}
