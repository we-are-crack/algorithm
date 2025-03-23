import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] pasture = new int[n + 1][n + 1];
        boolean[][][][] road = new boolean[n + 1][n + 1][n + 1][n + 1];

        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            road[r1][c1][r2][c2] = true;
            road[r2][c2][r1][c1] = true;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            pasture[y][x] = 1;
        }

        int answer = 0;
        boolean[][] visit = new boolean[n + 1][n + 1];
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                if (visit[y][x]) {
                    continue;
                }

                int result = bfs(pasture, road, visit, y, x);
                k -= result;
                answer += result * k;
            }
        }

        System.out.println(answer);
    }

    /**
     * 길이 이어진 한 구역에 존재하는 소의 마리 수 리턴
     */
    private static int bfs(int[][] pasture, boolean[][][][] road, boolean[][] visit, int y, int x) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{y, x});
        visit[y][x] = true;

        int[][] dir = {{-1 ,0}, {0, 1}, {1, 0}, {0, -1}};
        int count = pasture[y][x];
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (checkRange(pasture.length, nr, nc) && !road[r][c][nr][nc] && !visit[nr][nc]) {
                    que.add(new int[]{nr, nc});
                    visit[nr][nc] = true;
                    count += pasture[nr][nc];
                }
            }
        }

        return count;
    }

    private static boolean checkRange(int size, int r, int c) {
        return 0 < r && r < size && 0 < c && c < size;
    }
}