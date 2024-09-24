import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] picture = new String[n];

        for (int i = 0; i < n; i++) {
            picture[i] = br.readLine();
        }

        boolean[][] visitedNormal = new boolean[n][n];
        boolean[][] visitedColor = new boolean[n][n];
        int zoneNormal = 0;
        int zoneWeakness = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedNormal[i][j]) {
                    bfs(picture, i, j, visitedNormal, false);
                    zoneNormal++;
                }

                if (!visitedColor[i][j]) {
                    bfs(picture, i, j, visitedColor, true);
                    zoneWeakness++;
                }
            }
        }

        System.out.println(zoneNormal + " " + zoneWeakness);
    }

    private static void bfs(String[] picture, int x, int y, boolean[][] visited, boolean isColorWeakness) {
        char color = picture[x].charAt(y);
        Queue<int[]> q = new LinkedList<>();
        q. offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = cur[0] + dx[i];
                int newY = cur[1] + dy[i];
                
                if (isValid(newX, newY) && !visited[newX][newY] && isSameColor(color, picture[newX].charAt(newY), isColorWeakness)) {
                    visited[newX][newY] = true;
                    q. offer(new int[]{newX, newY});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static boolean isSameColor(char baseColor, char checkingColor, boolean isColorWeakness) {
        if (baseColor != 'B' && isColorWeakness) {
            return checkingColor == 'R' || checkingColor == 'G';
        }

        return baseColor == checkingColor;
    }
}