import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[][] paper;
    private static final int[] dRow = {0, 1, 0};
    private static final int[] dCol = {1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                answer = Math.max(answer, dfs(i, j, paper[i][j], 0, 1, visited));
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static int dfs(int row, int col, int sum, int max, int cnt, boolean[][] visited) {
        if (cnt == 4) {
            return Math.max(max, sum);
        }

        for (int i = 0; i < 3; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];

            if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                visited[newRow][newCol] = true;

                if (cnt == 2) {
                    max = Math.max(max, dfs(row, col, sum + paper[newRow][newCol], max, cnt + 1, visited));
                }

                max = Math.max(max, dfs(newRow, newCol, sum + paper[newRow][newCol], max, cnt + 1, visited));
                visited[newRow][newCol] = false;
            }
        }

        return max;
    }

    private static boolean isValid(int row, int col) {
         return row >= 0 && row < n && col >= 0 && col < m;
    }
}