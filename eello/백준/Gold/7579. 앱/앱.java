import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] apps = new int[n][2];
        StringTokenizer memSt = new StringTokenizer(br.readLine());
        StringTokenizer costSt = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 0; i < n; i++) {
            apps[i][0] = Integer.parseInt(memSt.nextToken());
            apps[i][1] = Integer.parseInt(costSt.nextToken());
            total += apps[i][1];
        }

        int[] dp = new int[total + 1];
        for (int i = 0; i < n; i++) {
            for (int c = dp.length - 1; c >= apps[i][1]; c--) {
                dp[c] = Math.max(dp[c], dp[c - apps[i][1]] + apps[i][0]);
            }
        }

        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= m) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
