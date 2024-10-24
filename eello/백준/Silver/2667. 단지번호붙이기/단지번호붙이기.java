import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int section = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] == '1') {
                    section++;
                    pq.add(bfs(map, r, c));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(section).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(char[][] map, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        map[r][c] = '0';

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int area = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            area++;

            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];

                if (checkRange(map, nr, nc) && map[nr][nc] == '1') {
                    queue.add(new int[]{nr, nc});
                    map[nr][nc] = '0';
                }
            }
        }

        return area;
    }

    private static boolean checkRange(char[][] map, int r, int c) {
        int n = map.length;
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
