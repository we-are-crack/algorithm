import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] indegree = new int[n + 1];
		List<Integer>[] adj = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			indegree[b]++;
			adj[a].add(b);
		}

		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				que.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!que.isEmpty()) {
			int student = que.poll();
			sb.append(student).append(" ");

			for (Integer stu : adj[student]) {
				indegree[stu]--;
				if (indegree[stu] == 0) {
					que.offer(stu);
				}
			}
		}

		System.out.println(sb);
	}
}
