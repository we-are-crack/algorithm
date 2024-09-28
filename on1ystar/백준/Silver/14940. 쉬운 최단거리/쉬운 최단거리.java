import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[] target = new int[2];;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(target);
        visited[target[0]][target[1]] = true;
        map[target[0]][target[1]] = 0;

        while (!q.isEmpty()) {
            int[] nowPos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = nowPos[0] + dr[i];
                int nc = nowPos[1] + dc[i];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    map[nr][nc] += map[nowPos[0]][nowPos[1]];
                    q.add(new int[] {nr, nc});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    sb.append("-1 ");
                } else {
                    sb.append(map[i][j] + " ");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}