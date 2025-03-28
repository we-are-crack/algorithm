import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int N, K, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] roads = new int[N + 1][N + 1];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int fromR = Integer.parseInt(st.nextToken());
            int fromC = Integer.parseInt(st.nextToken());
            int toR = Integer.parseInt(st.nextToken());
            int toC = Integer.parseInt(st.nextToken());
            roads[fromR][fromC] += getBitDirection(toR - fromR, toC - fromC);
            roads[toR][toC] += getBitDirection(fromR - toR, fromC - toC);
        }

        boolean[][] farm = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            farm[r][c] = true;
        }

        int answer = 0;
        int totalCows = K;
        boolean[][] visited = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    int cows = bfs(farm, roads, new int[]{i, j}, visited);
                    totalCows -= cows;
                    answer += cows * totalCows;
                }
            }
        }

        System.out.println(answer);
    }

    /**
     * 1(0001) : up
     * 2(0010) : right
     * 4(0100) : down
     * 8(1000) : left
     */
    private static int getBitDirection(int r, int c) {
        for (int i = 0; i < 4; i++) {
            if (r == direction[i][0] && c == direction[i][1]) {
                return 1 << i;
            }
        }
        return 0;
    }

    private static int bfs(boolean[][] farm, int[][] roads, int[] start, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(start);
        int cows = 0;

        while (!q.isEmpty()) {
            int[] now = q.removeFirst();

            if (farm[now[0]][now[1]]) {
                cows++;
            }

            for (int i = 0; i < 4; i++) {
                int[] next = new int[]{now[0] + direction[i][0], now[1] + direction[i][1]};
                int bitDirection = getBitDirection(next[0] - now[0], next[1] - now[1]);
                if (!valid(next[0], next[1]) || visited[next[0]][next[1]]) {
                    continue;
                }

                if ((roads[now[0]][now[1]] & bitDirection) == 0) {
                    visited[next[0]][next[1]] = true;
                    q.addLast(next);
                }
            }
        }

        return cows;
    }

    private static boolean valid(int r, int c) {
        return 0 < r && r <= N && 0 < c && c <= N;
    }
}