import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static final int INF = 1_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] fuel = new int[n][m];
        int[][][] dp = new int[n][m][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], INF);
                fuel[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dir = {{1, -1}, {1, 0}, {1, 1}}; // LEFT_DOWN, DOWN, RIGHT_DOWN
        for (int c = 0; c < m; c++) {
            for (int d = 0; d < dir.length; d++) {
                dp[0][c][d] = fuel[0][c];
            }
        }

        for (int r = 0; r < n - 1; r++) {
            for (int c = 0; c < m; c++) {
                for (int nd = 0; nd < dir.length; nd++) {
                    int nr = r + dir[nd][0], nc = c + dir[nd][1];
                    if (check(nr, nc)) {
                        for (int pd = 0; pd < dir.length; pd++) {
                            if (nd == pd) continue;
                            dp[nr][nc][nd] = Math.min(dp[nr][nc][nd], dp[r][c][pd] + fuel[nr][nc]);
                        }
                    }
                }
            }
        }

        int answer = INF;
        for (int c = 0; c < m; c++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[n - 1][c][d]);
            }
        }
        
        System.out.println(answer);
    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
