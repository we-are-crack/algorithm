import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((a) -> a[2]));
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(
				new int[] {
					Integer.parseInt(st.nextToken()), // a
					Integer.parseInt(st.nextToken()), // b
					Integer.parseInt(st.nextToken()), // c
				}
			);
		}

		int[] parent = new int[v + 1];
		for (int i = 0; i <= v; i++) {
			parent[i] = i;
		}

		int answer = 0, connect = 0;
		while (!pq.isEmpty() && connect < v) {
			int[] edge = pq.poll();
			if (union(parent, edge)) {
				answer += edge[2];
				connect++;
			}
		}

		System.out.println(answer);
	}

	private static boolean union(int[] parent, int[] edge) {
		int p1 = findParent(parent, edge[0]);
		int p2 = findParent(parent, edge[1]);

		if (p1 != p2) {
			if (p1 > p2)parent[p1] = p2;
			else parent[p2] = p1;

			return true;
		}

		return false;
	}

	private static int findParent(int[] parent, int node) {
		if (parent[node] == node) {
			return node;
		}

		return parent[node] = findParent(parent, parent[node]);
	}
}
