import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		int n, k;
		int[][] items;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		items = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			int w = items[i][0];
			int v = items[i][1];
			for (int j = 1; j <= k; j++) {
				if (j < w) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
				}
			}
		}

		System.out.println(dp[n][k]);
	}
}
