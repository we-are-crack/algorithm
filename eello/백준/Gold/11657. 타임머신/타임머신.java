import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] edges = new int[m][];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edges[i] = new int[] {a, b, c};
		}

		long[] cost = new long[n + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[1] = 0;

		while (n-- > 1) {
			for (int[] edge : edges) {
				if (cost[edge[0]] == INF) {
					continue;
				}

				if (cost[edge[1]] > cost[edge[0]] + edge[2]) {
					cost[edge[1]] = cost[edge[0]] + edge[2];
				}
			}
		}

		boolean cycle = false;
		for (int[] edge : edges) {
			if (cost[edge[0]] == INF) {
				continue;
			}

			if (cost[edge[1]] > cost[edge[0]] + edge[2]) {
				cost[edge[1]] = cost[edge[0]] + edge[2];
				cycle = true;
			}
		}

		StringBuilder answer = new StringBuilder();
		if (cycle) {
			answer.append(-1).append("\n");
		} else {
			for (int i = 2; i < cost.length; i++) {
				answer.append(cost[i] == INF ? -1 : cost[i]).append("\n");
			}
		}

		System.out.print(answer);
	}
}
