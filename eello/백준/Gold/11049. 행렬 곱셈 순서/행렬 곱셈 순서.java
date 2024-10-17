import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] matrix = new int[n][2];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			matrix[i][0] = r;
			matrix[i][1] = c;
		}

		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i] = 0;
		}

		for (int l = 1; l < n; l++) {
			for (int s = 0; s < n; s++) {
				int e = s + l;
				if (n <= e) {
					break;
				}

				for (int m = s; m < e; m++) {
					dp[s][e] = Math.min(
						dp[s][e],
						dp[s][m] + dp[m + 1][e] + (matrix[s][0] * matrix[m][1] * matrix[e][1])
					);
				}
			}
		}

		System.out.println(dp[0][n - 1]);
	}
}
