import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		while (t-- > 0) {
			int k = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] acc = new int[k];
			acc[0] = Integer.parseInt(st.nextToken());
			for (int i = 1; i < k; i++) {
				acc[i] = Integer.parseInt(st.nextToken()) + acc[i - 1];
			}

			long[][] dp = new long[k][k];
			for (int l = 1; l <= k; l++) {
				for (int s = 0; s < k; s++) {
					int e = s + l;
					if (k <= e) {
						break;
					}

					dp[s][e] = Long.MAX_VALUE;
					int size = s == 0 ? acc[e] : acc[e] - acc[s - 1];
					for (int j = s; j < e; j++) {
						dp[s][e] = Math.min(dp[s][e], dp[s][j] + dp[j + 1][e] + size);
					}
				}
			}

			answer.append(dp[0][k - 1]).append("\n");
		}

		System.out.print(answer);
	}
}
