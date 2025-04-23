import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX = 1_000_001;

    private static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] map = new int[r][c];
        int[] jihun = new int[2];
        List<int[]> fires = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                char elem = row[j];

                if (elem == '#') {
                    map[i][j] = -1;
                } else if (elem == 'J') {
                    jihun = new int[]{i, j};
                } else if (elem == 'F') {
                    map[i][j] = 1;
                    fires.add(new int[]{i, j});
                } else map[i][j] = MAX;
            }
        }

        fireBfs(map, fires);
        int result = jihunBfs(map, jihun[0], jihun[1]);
        System.out.print(result == -1 ? "IMPOSSIBLE" : result);
    }

    private static void fireBfs(int[][] map, List<int[]> fires) {
        Queue<int[]> que = new ArrayDeque<>(fires);

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int time = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] cur = que.poll();

                for (int[] d : dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];

                    if (checkRange(nr, nc) && map[nr][nc] == MAX) {
                        map[nr][nc] = time;
                        que.add(new int[]{nr, nc});
                    }
                }
            }

            time++;
        }
    }

    private static int jihunBfs(int[][] map, int sr, int sc) {
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[r][c];

        que.add(new int[]{sr, sc});
        visit[sr][sc] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int time = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] cur = que.poll();

                for (int[] d : dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];

                    if (!checkRange(nr, nc)) {
                        return time;
                    }

                    if (!visit[nr][nc] && time < map[nr][nc]) {
                        que.add(new int[]{nr, nc});
                        visit[nr][nc] = true;
                    }
                }
            }

            time++;
        }

        return -1;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}
