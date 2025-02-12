import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] mars = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mars[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = mars[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + mars[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[][] temp = new int[2][m];

            int left = 0, right = m - 1;
            temp[0][left] = dp[i - 1][left] + mars[i][left];
            temp[1][right] = dp[i - 1][right] + mars[i][right];
            while (left < m - 1) {
                left++;
                right--;

                temp[0][left] = Math.max(dp[i - 1][left], temp[0][left - 1]) + mars[i][left];
                temp[1][right] = Math.max(dp[i - 1][right], temp[1][right + 1]) + mars[i][right];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
