import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G3
 * 2146 - 다리 만들기
 * https://www.acmicpc.net/problem/2146
 */
public class Main {

    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int island = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    bfs(new Pos(i, j), island++);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (1 < map[i][j] && map[i][j] < island - 1 && isEdge(i, j)) {
                    answer = Math.min(answer, getBridgeLength(new Pos(i, j)));
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isEdge(int y, int x) {
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (!checkRange(ny, nx))
                continue;

            if (map[ny][nx] == 0)
                return true;
        }

        return false;
    }

    private static void bfs(Pos start, int island) {
        Queue<Pos> q = new LinkedList<>();

        q.offer(start);
        map[start.y][start.x] = island;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] d : dir) {
                int ny = now.y + d[0];
                int nx = now.x + d[1];

                if (checkRange(ny, nx) && map[ny][nx] == 1) {
                    map[ny][nx] = island;
                    q.offer(new Pos(ny, nx));
                }
            }
        }
    }

    private static int getBridgeLength(Pos start) {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] v = new boolean[n][n];

        q.offer(start);
        v[start.y][start.x] = true;
        int island = map[start.y][start.x];

        int length = -1;
        while (!q.isEmpty()) {
            length++;
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Pos now = q.poll();

                for (int[] d : dir) {
                    int ny = now.y + d[0];
                    int nx = now.x + d[1];

                    if (checkRange(ny, nx) && map[ny][nx] != island && !v[ny][nx]) {
                        if (map[ny][nx] != 0) {
                            return length;
                        }

                        v[ny][nx] = true;
                        q.offer(new Pos(ny, nx));
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}