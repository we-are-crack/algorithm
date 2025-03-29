import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] maze = new char[R][C];
        Deque<int[]> manQ = new ArrayDeque<>();
        Deque<int[]> fireQ = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = row.charAt(j);

                if (maze[i][j] == 'J') {
                    manQ.add(new int[]{i, j});
                } else if (maze[i][j] == 'F') {
                    fireQ.add(new int[]{i, j});
                }
            }
        }

        int time = 0;
        while (!manQ.isEmpty()) {
            time++;
            bfs(maze, fireQ, false);
            if (bfs(maze, manQ, true)) {
                System.out.println(time);
                return;
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    private static boolean bfs(char[][] maze, Deque<int[]> q, boolean isMan) {
        int cnt = q.size();
        for (int i = 0; i < cnt; i++) {
            int[] pos = q.removeFirst();

            for (int j = 0; j < 4; j++) {
                int nr = pos[0] + dr[j];
                int nc = pos[1] + dc[j];

                if (!valid(nr, nc)) {
                    if (isMan) {
                        return true;
                    }
                    continue;
                }

                if (isMan) {
                    if (maze[nr][nc] == '.') {
                        maze[nr][nc] = 'J';
                        q.addLast(new int[]{nr, nc});
                    }
                } else {
                    if (maze[nr][nc] == 'J' || maze[nr][nc] == '.') {
                        maze[nr][nc] = 'F';
                        q.addLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}