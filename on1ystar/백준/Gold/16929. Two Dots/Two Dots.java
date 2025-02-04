import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String[] dots = new String[n];
        for (int i = 0; i < n; i++) {
            dots[i] = br.readLine();
        }

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;

                    if (dfs(dots, new Pos(i, j), new Pos(i, j), visited)) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }

        System.out.println("No");
    }

    private static boolean dfs(String[] dots, Pos pre, Pos now, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            Pos next = new Pos(now.r + dr[i], now.c + dc[i]);

            if (valid(next.r, next.c) && !pre.equals(next) && dots[next.r].charAt(next.c) == dots[now.r].charAt(now.c)) {
                if (visited[next.r][next.c]) {
                    return true;
                }

                visited[next.r][next.c] = true;
                if (dfs(dots, now, next, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pos) {
                Pos pos = (Pos) obj;
                return this.r == pos.r && this.c == pos.c;
            }
            return false;
        }
    }
}