import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1,};
    private static int m;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] box = new int[n][m];

        int unripeTomatoCount = 0;
        Queue<int[]> ripeTomatoes = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int tomato = Integer.parseInt(st.nextToken());

                if (tomato == 1) {
                    ripeTomatoes.add(new int[]{i, j});
                } else if (tomato == 0) {
                    unripeTomatoCount++;
                }

                box[i][j] = tomato;
            }
        }

        int days = 0;
        while (!ripeTomatoes.isEmpty()) {
            pastOneDay(ripeTomatoes, box);
            days++;
            unripeTomatoCount -= ripeTomatoes.size();
        }

        if (unripeTomatoCount == 0) {
            System.out.println(days - 1);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean validPosition(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void pastOneDay(Queue<int[]> ripeTomatoes, int[][] box) {
        int ripeTomatoCount = ripeTomatoes.size();
        
        for (int i = 0; i < ripeTomatoCount; i++) {
            int[] tomato = ripeTomatoes.poll();

            for (int d = 0; d < 4; d++) {
                int r = tomato[0] + dr[d];
                int c = tomato[1] + dc[d];

                if (validPosition(r, c) && box[r][c] == 0) {
                    box[r][c] = 1;
                    ripeTomatoes.offer(new int[]{r, c});
                }
            }
        }
    }
}