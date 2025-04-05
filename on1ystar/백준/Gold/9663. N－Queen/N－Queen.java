import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    private static int n, answer;
    private static int[] dr = {1, 1, 1};
    private static int[] dc = {1, 0, -1};
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        solve(0);
        System.out.println(answer);
    }

    private static void solve(int r) {
        if (r == n) {
            answer++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if (board[r][j] == 0) {
                checkingBoard(r, j, 1);
                solve(r + 1);
                checkingBoard(r, j, -1);
            }
        }
    }

    private static void checkingBoard(int r, int c, int setting) {
        for (int i = 0; i < 3; i++) {
            int j = 2;
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            while (valid(nextR, nextC)) {
                board[nextR][nextC] += setting;
                nextR = r + dr[i] * j;
                nextC = c + dc[i] * j;
                j++;
            }
        }
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}