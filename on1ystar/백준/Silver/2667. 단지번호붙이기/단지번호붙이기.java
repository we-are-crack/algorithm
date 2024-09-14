import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dr = new int[] {0, 0, 1, -1};
    private static final int[] dc = new int[] {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] map = new String[n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine();
        }

        PriorityQueue<Integer> complex = new PriorityQueue<>();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i].charAt(j) == '0' || visited[i][j]) { continue; }

                q.offer(new int[]{i, j});
                int house = 1;
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    int[] pos = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int r = pos[0] + dr[d];
                        int c = pos[1] + dc[d];
                        if (0 <= r && r < n && 0 <= c && c < n && !visited[r][c] && map[r].charAt(c) == '1') {
                            visited[r][c] = true;
                            house++;
                            q.add(new int[]{r, c});
                        }
                    }
                }

                complex.add(house);
            }
        }

        System.out.println(complex.size());
        while (!complex.isEmpty()) {
            System.out.println(complex.poll());
        }
    }
}