import java.io.*;

public class Main {

    private static int n, answer;
    private static final int[] dr = {0, 1};
    private static final int[] dc = {1, 0};
    private static char[][] candies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        candies = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < n; j++) {
                candies[i][j] = s.charAt(j);
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                for (int d = 0; d < 2; d++) {
                    int r2 = r + dr[d];
                    int c2 = c + dc[d];

                    if (!valid(r2, c2)) { continue; }

                    change(r, c, r2, c2);

                    eatRow(r);
                    eatCol(c);
                    if (d == 0) {
                        eatCol(c2);
                    } else {
                        eatRow(r2);
                    }

                    change(r, c, r2, c2);
                }
            }
        }

        System.out.println(answer);
    }

    private static void change(int r1, int c1, int r2, int c2) {
        char temp;
        temp = candies[r1][c1];
        candies[r1][c1] = candies[r2][c2];
        candies[r2][c2] = temp;
    }

    private static void eatRow(int r) {
        eatLine(r, true);
    }

    private static void eatCol(int c) {
        eatLine(c, false);
    }

    private static void eatLine(int index, boolean isRow) {
        char pre = isRow ? candies[index][0] : candies[0][index];
        int cnt = 1;
        int max = 1;

        for (int i = 1; i < n; i++) {
            char current = isRow ? candies[index][i] : candies[i][index];
            if (pre == current) {
                cnt++;
            } else {
                max = Math.max(max, cnt);
                cnt = 1;
                pre = current;
            }
        }

        max = Math.max(max, cnt);
        answer = Math.max(answer, max);
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}