import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n];
            int[][] d = new int[2][n + 2];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                stickers[0][j] = Integer.parseInt(st1.nextToken());
                stickers[1][j] = Integer.parseInt(st2.nextToken());
                d[0][j + 2] = stickers[0][j] + Math.max(d[1][j + 1], d[1][j]);
                d[1][j + 2] = stickers[1][j] + Math.max(d[0][j + 1], d[0][j]);
            }

            sb.append(Math.max(d[0][n + 1], d[1][n + 1]));
            sb.append("\n");
        }

        System.out.print(sb);
    }
}