import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] miro = new char[n][m];
        for (int i = 0; i < n; i++) {
            miro[i] = br.readLine().toCharArray();
        }

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        que.add(new int[]{0, 0, 1});
        visit[0][0] = true;

        int answer = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];

            if (r == n - 1 && c == m - 1) {
                answer = cur[2];
                break;
            }

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (checkRange(nr, nc) && miro[nr][nc] == '1' && !visit[nr][nc]) {
                    visit[nr][nc] = true;
                    que.add(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
