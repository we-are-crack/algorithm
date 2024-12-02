import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int size = 0;
        int tc = 1;
        while ((size = Integer.parseInt(br.readLine())) != 0) {
            int[][] map = new int[size][size];
            for (int r = 0; r < size; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < size; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer.append("Problem ")
                    .append(tc++)
                    .append(": ")
                    .append(dijkstra(map))
                    .append("\n");
        }

        System.out.println(answer);
    }

    private static int dijkstra(int[][] map) {
        int size = map.length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int[][] cost = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, map[0][0]});
        cost[0][0] = map[0][0];

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            if (cost[node[0]][node[1]] < node[2]) {
                continue;
            }

            for (int[] d : dir) {
                int nr = node[0] + d[0];
                int nc = node[1] + d[1];

                if (checkRange(size, nr, nc) && map[nr][nc] + node[2] < cost[nr][nc]) {
                    cost[nr][nc] = map[nr][nc] + node[2];
                    queue.add(new int[]{nr, nc, cost[nr][nc]});
                }
            }
        }

        return cost[size - 1][size - 1];
    }

    private static boolean checkRange(int size, int r, int c) {
        return 0 <= r && r < size && 0 <= c && c < size;
    }
}
