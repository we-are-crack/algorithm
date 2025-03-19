import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int[][] paper;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] cnt = new int[2];
        cut(new Pos(0, 0), n, cnt);

        for (int i = 0; i < 2; i++) {
            System.out.println(cnt[i]);
        }
    }

    private static void cut(Pos start, int size, int[] cnt) {
        int color = paper[start.r][start.c];
        
        for (int i = start.r; i < start.r + size; i++) {
            for (int j = start.c; j < start.c + size; j++) {
                if (color != paper[i][j]) {
                    color = -1;
                    break;
                }
            }
        }

        if (color == -1) {
            size /= 2;
            cut(start, size, cnt);
            cut(new Pos(start.r + size, start.c), size, cnt);
            cut(new Pos(start.r, start.c + size), size, cnt);
            cut(new Pos(start.r + size, start.c + size), size, cnt);
        } else {
            cnt[color]++;
        }
    }

    static class Pos {
        private final int r;
        private final int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}