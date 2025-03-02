import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int[] dr = {-2, -2, 0, 0, 2, 2};
    private static int[] dc = {-1, 1, -2, 2, -1, 1};
    private static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] start = new int[2];
        for (int i = 0; i < 2; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }
        int[] end = new int[2];
        for (int i = 0; i < 2; i++) {
            end[i] = Integer.parseInt(st.nextToken());
        }

        int answer = -1;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        q.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] pos = q.removeFirst();

            if (pos[0] == end[0] && pos[1] == end[1]) {
                answer = pos[2];
                break;
            }

            for (int i = 0; i < dr.length; i++) {
                int nr = pos[0] + dr[i];
                int nc = pos[1] + dc[i];

                if (valid(nr ,nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.addLast(new int[]{nr, nc, pos[2] + 1});
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean valid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}