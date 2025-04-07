import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int n;
    private static int[][] map;
    private static List<List<int[]>> coastline = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int r = 0; r < n; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visit = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] == 0 || visit[r][c]) {
                    continue;
                }

                bfs(r, c, visit);
            }
        }

        int answer = Integer.MAX_VALUE;
        int area = coastline.size();
        for (int i = 0; i < area; i++) {
            for (int j = i + 1; j < area; j++) {
                answer = Math.min(answer, getMinLength(coastline.get(i), coastline.get(j)));
            }
        }

        System.out.print(answer);
    }

    private static int getMinLength(List<int[]> cl1, List<int[]> cl2) {
        int min = Integer.MAX_VALUE;
        for (int[] a : cl1) {
            for (int[] b : cl2) {
                min = Math.min(min, Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) - 1);
            }
        }

        return min;
    }

    private static void bfs(int sr, int sc, boolean[][] visit) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sr, sc});
        visit[sr][sc] = true;

        List<int[]> cl = new ArrayList<>();

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];

            if (isCoastline(r, c)) {
                cl.add(cur);
            }

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (checkRange(nr, nc) && map[nr][nc] == 1 && !visit[nr][nc]) {
                    visit[nr][nc] = true;
                    que.add(new int[]{nr, nc});
                }
            }
        }

        coastline.add(cl);
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    private static boolean isCoastline(int r, int c) {
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (checkRange(nr, nc) && map[nr][nc] == 0) {
                return true;
            }
        }

        return false;
    }
}
