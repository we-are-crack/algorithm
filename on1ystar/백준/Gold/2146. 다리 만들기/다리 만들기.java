import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int islandCnt = 1;
    private static int minBridge = 10_000;
    private static int[][] country;
    private static int[][] visited;
    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        country = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                country[i][j] = Integer.parseInt(st.nextToken()) - 1;
                visited[i][j] = 10_000;
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (country[i][j] == 0) {
                    country[i][j] = islandCnt;
                    q.add(new int[]{i, j});
                    setIslandCnt(q);
                    islandCnt++;
                } else if (country[i][j] == -1) {
                    int island = getIslandIfCoast(i, j);
                    if (island != 0) {
                        setBridge(new int[]{i, j}, island);
                    }
                }
            }
        }

        System.out.println(minBridge);
    }

    private static void setIslandCnt(Deque<int[]> q) {
        while (!q.isEmpty()) {
            int[] now = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (valid(nr, nc) && country[nr][nc] == 0) {
                    country[nr][nc] = islandCnt;
                    q.addLast(new int[]{nr, nc});
                }
            }
        }
    }

    private static void setBridge(int[] pos, int island) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(pos);
        visited[pos[0]][pos[1]] = 1;

        while (!q.isEmpty()) {
            int[] now = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (!valid(nr, nc) || country[nr][nc] == island) {
                    continue;
                }

                if (country[nr][nc] == -1) {
                    if (visited[now[0]][now[1]] + 1 < visited[nr][nc]) {
                        visited[nr][nc] = visited[now[0]][now[1]] + 1;
                        q.add(new int[]{nr, nc});
                    }
                } else {
                    minBridge = Math.min(minBridge, visited[now[0]][now[1]]);
                    return;
                }
            }
        }
    }

    private static int getIslandIfCoast(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (valid(nr, nc) && country[nr][nc] >= 1) {
                return country[nr][nc];
            }
        }

        return 0;
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}