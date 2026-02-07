import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int size = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    size = Math.max(size, bfs(new int[]{i, j}, paper, visited));
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(size);
    }

    private static int bfs(int[] start, int[][] paper, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        int size = 1;
        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int i = 0; i < 4; i++) {
                int[] next = new int[]{dx[i] + pos[0], dy[i] + pos[1]};

                if (!valid(next) || visited[next[0]][next[1]] || paper[next[0]][next[1]] == 0) {
                    continue;
                }

                q.offer(next);
                visited[next[0]][next[1]] = true;
                size++;
            }
        }

        return size;
    }

    private static boolean valid(int[] pos) {
        return 0 <= pos[0] && pos[0] < n && 0 <= pos[1] && pos[1] < m;
    }
}