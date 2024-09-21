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
        List<Queue<int[]>> ripeTomatoes = new ArrayList<>();
        ripeTomatoes.add(new LinkedList<>());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int tomato = Integer.parseInt(st.nextToken());

                if (tomato == 1) {
                    ripeTomatoes.get(0).add(new int[]{i, j});
                } else if (tomato == 0) {
                    unripeTomatoCount++;
                }

                box[i][j] = tomato;
            }
        }

        int days = ripeTomatoes.size() - 1;
        while (!ripeTomatoes.get(days).isEmpty()) {
            ripeTomatoes.add(new LinkedList<>(pastOneDay(ripeTomatoes.get(days), box)));
            days++;
            unripeTomatoCount -= ripeTomatoes.get(days).size();
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

    private static Queue<int[]> pastOneDay(Queue<int[]> ripeTomatoes, int[][] box) {
        Queue<int[]> nextRipeTomatoes = new LinkedList<>();

        while (!ripeTomatoes.isEmpty()) {
            int[] tomato = ripeTomatoes.poll();

            for (int i = 0; i < 4; i++) {
                int r = tomato[0] + dr[i];
                int c = tomato[1] + dc[i];

                if (validPosition(r, c) && box[r][c] == 0) {
                    box[r][c] = 1;
                    nextRipeTomatoes.offer(new int[]{r, c});
                }
            }

        }

        return nextRipeTomatoes;
    }
}