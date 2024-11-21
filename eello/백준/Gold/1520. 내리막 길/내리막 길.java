import java.io.*;
import java.util.*;

public class Main {

    private static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int[][] path = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{map[0][0], 0, 0});
        path[0][0] = 1;

        while (!pq.isEmpty()) {
            int[] point = pq.poll();

            int r = point[1];
            int c = point[2];

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (checkRange(nr, nc) && map[nr][nc] < map[r][c]) {
                    path[nr][nc] += path[r][c];
                    if (!visit[nr][nc]) {
                        visit[nr][nc] = true;
                        pq.offer(new int[]{map[nr][nc], nr, nc});
                    }
                }
            }
        }

        System.out.println(path[m - 1][n - 1]);
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}
