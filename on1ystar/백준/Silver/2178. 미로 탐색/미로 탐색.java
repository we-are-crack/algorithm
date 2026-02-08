import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < m; j++) {
                maze[i][j] = s.charAt(j) - '0';
            }
        }

        Deque<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 1));
        maze[0][0] = 0;
        while (!q.isEmpty()) {
            Pos pos = q.poll();

            if (pos.getX() == n - 1 && pos.getY() == m - 1) {
                System.out.println(pos.getPath());
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = pos.getX() + dx[i];
                int ny = pos.getY() + dy[i];

                if (!valid(nx, ny) || maze[nx][ny] == 0) continue;

                q.offer(new Pos(nx, ny, pos.getPath() + 1));
                maze[nx][ny] = 0;
            }
        }

    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static class Pos {
        private final int x;
        private final int y;
        private final int path;

        public Pos(int x, int y, int path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPath() {
            return path;
        }
    }
}