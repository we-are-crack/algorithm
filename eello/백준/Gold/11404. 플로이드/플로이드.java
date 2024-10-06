import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] costs = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(costs[i], Integer.MAX_VALUE);
			costs[i][i] = 0;
		}

		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (costs[a][b] > c) {
				costs[a][b] = c;
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
						costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
					}
				}
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				answer.append(costs[i][j] == Integer.MAX_VALUE ? 0 : costs[i][j]).append(" ");
			}
			answer.append("\n");
		}

		System.out.print(answer);
	}
}
