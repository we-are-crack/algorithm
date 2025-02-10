import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static boolean cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[][] visit = new int[n][m];
        loop1 : for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] != 0) {
                    continue;
                }

                dfs(board, visit, i, j, 1);
                if (cycle) {
                    break loop1;
                }
            }
        }

        System.out.println(cycle ? "Yes" : "No");
    }

    private static void dfs(char[][] board, int[][] visit, int r, int c, int k) {
        if (cycle) {
            return;
        }

        visit[r][c] = k;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (checkRange(nr, nc) && board[r][c] == board[nr][nc]) {
                if (visit[nr][nc] == 0) {
                    dfs(board, visit, nr, nc, k + 1);
                } else if (Math.abs(visit[r][c] - visit[nr][nc]) >= 3) {
                    cycle = true;
                    return;
                }
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
