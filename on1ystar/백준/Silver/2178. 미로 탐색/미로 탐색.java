import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        solve(maze, n, m);
    }

    private static void solve(int[][] maze, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] nowPos = q.poll();
            if (nowPos[0] == n - 1 && nowPos[1] == m - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int[] nextPos = new int[] {nowPos[0] + dy[i], nowPos[1] + dx[i]};
                if (nextPos[0] < 0 || nextPos[0] >= n || nextPos[1] < 0 || nextPos[1] >= m) { continue; }
                if (maze[nextPos[0]][nextPos[1]] == 1) {
                    maze[nextPos[0]][nextPos[1]] = maze[nowPos[0]][nowPos[1]] + 1;
                    q.offer(new int[]{nextPos[0], nextPos[1]});
                }
            }
        }

        System.out.println(maze[n - 1][m - 1]);
    }
}