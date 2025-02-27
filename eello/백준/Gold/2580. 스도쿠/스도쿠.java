import java.io.*;
import java.util.*;

public class Main {

    private static List<int[]> empty = new ArrayList<>();
    private static int[][] board, square;
    private static int[] row, col;
    private static boolean complete;

    public static void main(String[] args) throws IOException {
        init();
        sudoku(0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        square = new int[3][3];
        row = new int[9];
        col = new int[9];

        for (int r = 0; r < 9; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 9; c++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 0) {
                    empty.add(new int[]{r, c});
                } else {
                    board[r][c] = val;

                    square[r / 3][c / 3] |= 1 << val;
                    row[r] |= 1 << val;
                    col[c] |= 1 << val;
                }
            }
        }
    }

    private static void sudoku(int idx) {
        if (complete) {
            return;
        }

        if (idx == empty.size()) {
            complete = true;
            printBoard();
            return;
        }

        int[] target = empty.get(idx);
        int r = target[0], c = target[1];
        for (int n = 1; n <= 9; n++) {
            if (!isAvailable(r, c, n)) {
                continue;
            }

            board[r][c] = n;
            square[r / 3][c / 3] |= 1 << n;
            row[r] |= 1 << n;
            col[c] |= 1 << n;

            sudoku(idx + 1);

            square[r / 3][c / 3] ^= 1 << n;
            row[r] ^= 1 << n;
            col[c] ^= 1 << n;
        }
    }

    private static boolean isAvailable(int r, int c, int num) {
        return (square[r / 3][c / 3] & (1 << num)) == 0
                && (row[r] & (1 << num)) == 0
                && (col[c] & (1 << num)) == 0;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(board[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
