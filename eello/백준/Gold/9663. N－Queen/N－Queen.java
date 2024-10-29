import java.io.*;
import java.util.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] queen = new int[n]; // queen[r] = c -> (r, c)에 퀸 존재
        System.out.println(solution(queen, 0));
    }

    private static int solution(int[] queen, int row) {
        if (row == n) {
            return 1;
        }

        int ret = 0;
        for (int col = 0; col < n; col++) {
            queen[row] = col;
            if (isPlaceable(queen, row, col)) {
                ret += solution(queen, row + 1);
            }
        }
        return ret;
    }

    private static boolean isPlaceable(int[] queen, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (queen[r] == col || Math.abs(row - r) == Math.abs(col - queen[r])) {
                return false;
            }
        }

        return true;
    }
}
