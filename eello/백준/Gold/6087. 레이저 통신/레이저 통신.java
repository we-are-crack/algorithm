import java.io.*;
import java.util.*;

public class Main {

    private static final char WALL = '*';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        char[][] map = new char[h][w];
        int[][] endpoint = new int[2][2];

        int ep = 0;
        for (int r = 0; r < h; r++) {
            char[] charArray = br.readLine().toCharArray();
            for (int c = 0; c < w; c++) {
                map[r][c] = charArray[c];
                if (map[r][c] == 'C') {
                    endpoint[ep][0] = r;
                    endpoint[ep][1] = c;
                    ep++;
                }
            }
        }

        Queue<int[]> que = new ArrayDeque<>();
        int[][][] visit = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }


        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int d = 0; d < 4; d++) {
            visit[endpoint[0][0]][endpoint[0][1]][d] = 0;

            int nr = endpoint[0][0] + dir[d][0];
            int nc = endpoint[0][1] + dir[d][1];

            if (checkRange(map, nr, nc) && map[nr][nc] != WALL) {
                que.offer(new int[]{nr, nc, d, 0});
                visit[nr][nc][d] = 0;
            }
        }

        int answer = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            int[] point = que.poll();
            int r = point[0], c = point[1], d = point[2], cnt = point[3];

            if (r == endpoint[1][0] && c == endpoint[1][1]) {
                answer = Math.min(answer, cnt);
            }

            for (int nd = 0; nd < 4; nd++) {
                if ((d + 2) % 4 == nd) {
                    continue;
                }

                int nr = r + dir[nd][0];
                int nc = c + dir[nd][1];
                int nextCnt = cnt + (d % 2 == nd % 2 ? 0 : 1);

                if (checkRange(map, nr, nc) && map[nr][nc] != WALL && nextCnt < visit[nr][nc][nd]) {
                    visit[nr][nc][nd] = nextCnt;
                    que.offer(new int[]{nr, nc, nd, nextCnt});
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean checkRange(char[][] map, int r, int c) {
        return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
    }
}
