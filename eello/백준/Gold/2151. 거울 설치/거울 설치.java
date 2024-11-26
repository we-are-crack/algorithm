import java.io.*;
import java.util.*;

public class Main {

    private static final char DOOR = '#', WALL = '*', EMPTY = '.';
    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int n;
    private static char[][] map;
    private static int[][] door = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        int dc = 0;
        for (int r = 0; r < n; r++) {
            char[] charArr = br.readLine().toCharArray();
            for (int c = 0; c < n; c++) {
                map[r][c] = charArr[c];
                if (map[r][c] == DOOR) {
                    door[dc][0] = r;
                    door[dc][1] = c;
                    dc++;
                }
            }
        }

        int[][][] visit = new int[n + 1][n + 1][4];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }

        for (int d = 0; d < DIR.length; d++) {
            visit[door[0][0]][door[0][1]][d] = 0;

            int sr = door[0][0] + DIR[d][0];
            int sc = door[0][1] + DIR[d][1];

            if (checkRange(sr, sc) && map[sr][sc] != WALL) {
                solution(visit, sr, sc, d, 0);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < DIR.length; d++) {
            answer = Math.min(answer, visit[door[1][0]][door[1][1]][d]);
        }

        System.out.println(answer);
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    private static void solution(int[][][] visit, int r, int c, int d, int mirror) {
        if (!checkRange(r, c) || map[r][c] == WALL) {
            return;
        }

        if (visit[r][c][d] <= mirror) {
            return;
        }

        visit[r][c][d] = mirror;
        if (map[r][c] == DOOR) {
            return;
        }

        if (map[r][c] == EMPTY) {
            int[] next = getNextPosition(r, c, d);
            solution(visit, next[0], next[1], d, mirror);
        } else {
            for (int nd = 0; nd < DIR.length; nd++) {
                if (d != nd && d % 2 == nd % 2) { // 다시 뒤로 되돌아가는 경우
                    continue;
                }

                int[] next = getNextPosition(r, c, nd);
                solution(visit, next[0], next[1], nd, d == nd ? mirror : mirror + 1);
            }
        }


    }

    private static int[] getNextPosition(int r, int c, int d) {
        while (true) {
            r = r + DIR[d][0];
            c = c + DIR[d][1];

            if (!checkRange(r, c) || map[r][c] != EMPTY) {
                break;
            }
        }

        return new int[]{r, c};
    }
}
