import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] marsMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        marsMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                marsMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = marsMap[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + marsMap[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[] rightDp = new int[m];
            int[] leftDp = new int[m];
            rightDp[0] = marsMap[i][0] + dp[i - 1][0];
            leftDp[m - 1] = marsMap[i][m - 1] + dp[i - 1][m - 1];

            // 오른쪽으로
            for (int j = 1; j < m; j++) {
                rightDp[j] = marsMap[i][j] + Math.max(rightDp[j - 1], dp[i - 1][j]);
            }

            // 왼쪽으로
            for (int j = m - 2; j >= 0; j--) {
                leftDp[j] = marsMap[i][j] + Math.max(leftDp[j + 1], dp[i - 1][j]);
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(leftDp[j], rightDp[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}