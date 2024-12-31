import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] score = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int country = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 3; j++) {
                score[country][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] selected = score[k];
        int rank = 1;
        for (int country = 1; country <= n; country++) {
            if (country == k) {
                continue;
            }

            if (compare(score[country], selected) > 0) {
                rank++;
            }
        }

        System.out.println(rank);
    }

    private static int compare(int[] c1, int[] c2) {
        if (c1[0] != c2[0]) {
            // 금메달 비교
            return c1[0] - c2[0];
        }

        if (c1[1] != c2[1]) {
            // 은메달 비교
            return c1[1] - c2[1];
        }

        // 동메달 비교
        return c1[2] - c2[2];
    }
}
