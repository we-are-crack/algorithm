import java.io.*;
import java.util.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, tsp(cost, i, i, 0, 0));
        }

        System.out.println(answer);
    }

    private static int tsp(int[][] cost, int start, int current, int k, int bitmask) {
        if (start == current && k != 0) {
            if (bitmask == (1 << n) - 1) {
                return k;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            if (cost[current][next] == 0 || (bitmask & (1 << next)) != 0) {
                continue;
            }

            ret = Math.min(ret, tsp(cost, start, next, k + cost[current][next], bitmask | 1 << next));
        }

        return ret;
    }
}
