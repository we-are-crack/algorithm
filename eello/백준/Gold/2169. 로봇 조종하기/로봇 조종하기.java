import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] mars = new int[n][m];
        for (int i = 0; i < mars.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mars[i].length; j++) {
                mars[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = mars[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + mars[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[] temp1 = new int[m];
            temp1[0] = dp[i - 1][0] + mars[i][0];
            for (int j = 1; j < m; j++) {
                temp1[j] = Math.max(dp[i - 1][j], temp1[j - 1]) + mars[i][j];
            }

            int[] temp2 = new int[m];
            temp2[m - 1] = dp[i - 1][m - 1] + mars[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                temp2[j] = Math.max(dp[i - 1][j], temp2[j + 1]) + mars[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(temp1[j], temp2[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
