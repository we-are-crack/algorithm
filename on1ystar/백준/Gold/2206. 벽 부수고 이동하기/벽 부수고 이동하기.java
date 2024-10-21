import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{1, 0, -1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};
    private static int n, m;
    private static int[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];

        String s;
        for (int i = 0; i < n; i++) {
            s= br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = s.charAt(j) - '0';
            }
        }

        Deque<Walking> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        boolean[][] visitedBreaking = new boolean[n][m];

        q.offer(new Walking(0, 0, 1, false));
        visited[0][0] = true;
        visitedBreaking[0][0] = true;
        while (!q.isEmpty()) {
            Walking walking = q.poll();

            if (walking.getR() == n - 1 && walking.getC() == m - 1) {
                System.out.println(walking.getWalkingPath());
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = walking.getR() + dr[i];
                int nc = walking.getC() + dc[i];

                if (!valid(nr, nc)) {
                    continue;
                }

                if (walking.hasBeenBroken()) {
                    if (!visitedBreaking[nr][nc] && maze[nr][nc] == 0) {
                        visitedBreaking[nr][nc] = true;
                        q.offer(new Walking(nr, nc, walking.getWalkingPath() + 1, walking.hasBeenBroken()));
                    }
                } else {
                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;

                        if (maze[nr][nc] == 0) {
                            q.offer(new Walking(nr, nc, walking.getWalkingPath() + 1, walking.hasBeenBroken()));
                        } else {
                            q.offer(new Walking(nr, nc, walking.getWalkingPath() + 1, true));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class Walking {
        private final int r;
        private final int c;
        private final int walkingPath;
        private final boolean broken;

        public Walking(int r, int c, int walkingPath, boolean broken) {
            this.r = r;
            this.c = c;
            this.walkingPath = walkingPath;
            this.broken = broken;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public int getWalkingPath() {
            return walkingPath;
        }

        public boolean hasBeenBroken() {
            return broken;
        }
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}