import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int m, n;
    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int[][] map, path;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        path = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        path[0][0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (path[r][c] != 0 || visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (valid(nr, nc) && map[r][c] < map[nr][nc]) {
                        if (path[nr][nc] != 0) {
                            path[r][c] += path[nr][nc];
                        } else {
                            visited[nr][nc] = true;
                            path[r][c] += findStart(nr, nc);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(path[i]));;
//        }

        System.out.println(path[m - 1][n - 1]);
    }

    private static int findStart(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (valid(nr, nc) && map[r][c] < map[nr][nc]) {

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    path[r][c] += findStart(nr, nc);
                } else {
                    if (path[nr][nc] != 0) {
                        path[r][c] += path[nr][nc];
                    }
                }
            }
        }

        return path[r][c];
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}