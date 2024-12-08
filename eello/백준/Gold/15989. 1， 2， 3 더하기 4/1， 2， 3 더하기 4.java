import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] input = new int[t];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < t; i++) {
            input[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, input[i]);
        }

        int[][] dp = new int[4][max + 1];
        for (int i = 1; i <= 3; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= max; j++) {
                dp[i][j] = dp[i - 1][j];
                if (i <= j) {
                    dp[i][j] += dp[i][j - i];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answer.append(dp[3][input[i]]).append("\n");
        }

        System.out.println(answer);
    }
}
