import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder answer = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) {
				break;
			}

			int[] robot = new int[2];
			char[][] map = new char[h][w];
			List<int[]> point = new ArrayList<>();
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 'o') {
						robot[0] = i;
						robot[1] = j;
						map[i][j] = '.';
						point.add(new int[]{i, j});
					} else if (map[i][j] == '*') {
						point.add(new int[]{i, j});
					}
				}
			}

			List<int[]>[] adj = new List[h * w + 1];
			for (int[] p : point) {
				adj[w * p[0] + p[1]] = getDist(map, p);
			}

			int r = w * robot[0] + robot[1];
			boolean[] visit = new boolean[adj.length];
			visit[r] = true;

			int result = solution(adj, visit, r, 0, point.size() - 1);
			answer.append(result != INF ? result : -1).append("\n");
		}

		System.out.print(answer);
	}

	private static List<int[]> getDist(char[][] map, int[] start) {
		int h = map.length;
		int w = map[0].length;

		List<int[]> result = new ArrayList<>();
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[h][w];

		queue.offer(new int[]{start[0], start[1], 0});
		visit[start[0]][start[1]] = true;

		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (map[cur[0]][cur[1]] == '*') {
				result.add(new int[]{w * cur[0] + cur[1], cur[2]});
			}

			for (int[] d : dir) {
				int nr = cur[0] + d[0];
				int nc = cur[1] + d[1];

				if (0 <= nr && nr < h && 0 <= nc && nc < w && map[nr][nc] != 'x' && !visit[nr][nc]) {
					visit[nr][nc] = true;
					queue.offer(new int[]{nr, nc, cur[2] + 1});
				}
			}
		}

		return result;
	}

	private static int solution(List<int[]>[] adj, boolean[] visit, int cur, int dist, int clean) {
		if (clean == 0) {
			return dist;
		}

		int ret = INF;
		for (int[] next : adj[cur]) {
			if (visit[next[0]]) {
				continue;
			}

			visit[next[0]] = true;
			ret = Math.min(ret, solution(adj, visit, next[0], dist + next[1], clean - 1));
			visit[next[0]] = false;
		}
		return ret;
	}
}
