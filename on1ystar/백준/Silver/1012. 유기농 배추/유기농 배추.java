import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[][] farm = new boolean[row][col];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                farm[r][c] = true;
            }

            int wormCnt = 0;
            for (int j = 0; j < farm.length; j++) {
                for (int l = 0; l < farm[j].length; l++) {
                    if (farm[j][l]) {
                        bfs(farm, new int[]{j, l});
                        wormCnt++;
                    }
                }
            }

            sb.append(wormCnt).append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs(boolean[][] farm, int[] start) {
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(start);

        while (!q.isEmpty()) {
            int[] now = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int[] next = new int[]{now[0] + dr[i], now[1] + dc[i]};

                if (valid(farm, next[0], next[1]) && farm[next[0]][next[1]]) {
                    farm[next[0]][next[1]] = false;
                    q.addLast(next);
                }
            }
        }
    }

    private static boolean valid(boolean[][] farm, int r, int c) {
        return 0 <= r && r < farm.length && 0 <= c && c < farm[0].length;
    }
}