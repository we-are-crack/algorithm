import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		int[] dist = new int[INF];
		Arrays.fill(dist, INF);

		pq.offer(new int[] {n, 0});
		dist[n] = 0;

		int answer = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cur[0] == k) {
				answer = cur[1];
				break;
			}

			offer(pq, dist, cur[0] * 2, cur[1]);
			offer(pq, dist, cur[0] + 1, cur[1] + 1);
			offer(pq, dist, cur[0] - 1, cur[1] + 1);
		}

		System.out.println(answer);
	}

	private static void offer(PriorityQueue<int[]> pq, int[] dist, int next, int nextTime) {
		if (0 <= next && next < INF && dist[next] > nextTime) {
			dist[next] = nextTime;
			pq.offer(new int[] {next, nextTime});
		}
	}
}
