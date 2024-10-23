import java.io.*;

public class Main {

    private static int n;
    private static final int[] direction = {0, 1, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] queens = new int[n];
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            queens[0] = i;
            solve(queens, board, 1);
        }

        System.out.println(answer);
    }

    private static void solve(int[] queens, int[][] board, int queenCount) {
        if (queenCount == n) {
            answer++;
            return;
        }

        addQueen(queens, board, queenCount);

        for (int i = 0; i < n; i++) {
            if (board[queenCount][i] == 0) {
                queens[queenCount] = i;
                solve(queens, board, queenCount + 1);
            }
        }

        removeQueen(queens, board, queenCount);
    }

    private static void calculateBoard(int[] queens, int[][] board, int queenCount, boolean isAdding) {
        for (int i = 0; i < queenCount; i++) {
            for (int d = 0; d < 3; d++) {
                int pos = queens[i] + (direction[d] * (queenCount - i));

                if (0 <= pos && pos < n) {
                    if (isAdding) {
                        board[queenCount][pos]++;
                    } else {
                        board[queenCount][pos]--;
                    }
                }
            }
        }
    }

    private static void addQueen(int[] queens, int[][] board, int queenCount) {
        calculateBoard(queens, board, queenCount, true);
    }

    private static void removeQueen(int[] queens, int[][] board, int queenCount) {
        calculateBoard(queens, board, queenCount, false);
    }
}