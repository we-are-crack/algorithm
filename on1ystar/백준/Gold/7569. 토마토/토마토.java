import java.io.*;
import java.util.*;

public class Main {

    private static int r, c, h;
    private static final int[] dr = new int[]{-1, 1, 0, 0, 0, 0};
    private static final int[] dc = new int[]{0, 0, -1, 1, 0, 0};
    private static final int[] dh = new int[]{0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][][] boxes = new int[h][r][c];
        int unripeTomatoCount = 0;
        Queue<int[]> ripeTomato = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < c; k++) {
                    int tomato = Integer.parseInt(st.nextToken());

                    if (tomato == 0) {
                        unripeTomatoCount++;
                    } else if (tomato == 1) {
                        ripeTomato.offer(new int[]{i, j, k});
                    }

                    boxes[i][j][k] = tomato;
                }
            }
        }

        int days = 0;
        int tomatoCountOfOneDay = ripeTomato.size();
        while (!ripeTomato.isEmpty() && unripeTomatoCount != 0) {
            int[] tomatoPos = ripeTomato.poll();
            tomatoCountOfOneDay--;

            for (int d = 0; d < 6; d++) {
                int height = tomatoPos[0] + dh[d];
                int row = tomatoPos[1] + dr[d];
                int col = tomatoPos[2] + dc[d];

                if (isValid(row, col, height) && boxes[height][row][col] == 0) {
                    boxes[height][row][col] = 1;
                    ripeTomato.offer(new int[]{height, row, col});
                    unripeTomatoCount--;
                }
            }

            if (unripeTomatoCount == 0) {
                days++;
                break;
            }

            if (tomatoCountOfOneDay == 0) {
                tomatoCountOfOneDay = ripeTomato.size();
                days++;
            }
        }

        if (unripeTomatoCount == 0) {
            System.out.println(days);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean isValid(int row, int column, int height) {
        return 0 <= row && row < r && 0 <= column && column < c && 0 <= height && height < h;
    }
}