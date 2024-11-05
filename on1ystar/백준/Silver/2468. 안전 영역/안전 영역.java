import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{1, 0, -1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};
    private static int n;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        int[] heights = new int[101];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                heights[num]++;
                max = Math.max(max, num);
            }
        }

        int answer = 1;
        int remainingSpace = n * n;

        for (int height = 1; height < max; height++) {
            if (heights[height] == 0) { continue; }
            if (remainingSpace <= answer) { break; }

            boolean[][] visited = new boolean[n][n];
            int safeZone = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > height) {
                        visited[i][j] = true;
                        dfs(i, j, height, map, visited);
                        safeZone++;
                    }
                }
            }

            answer = Math.max(answer, safeZone);
            remainingSpace -= heights[height];
        }

        System.out.println(answer);
    }

    private static void dfs(int r, int c, int height, int[][] map, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (valid(nr, nc) && !visited[nr][nc] && map[nr][nc] > height) {
                visited[nr][nc] = true;
                dfs(nr, nc, height, map, visited);
            }
        }
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}