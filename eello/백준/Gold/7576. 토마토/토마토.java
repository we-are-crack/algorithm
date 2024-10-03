import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new ArrayDeque<>();
		int[][] map = new int[n][m];

		int unripe = 0; // 익지 않은 토마토 수
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 1) {
					queue.offer(new int[] {r, c, 0});
				} else if (map[r][c] == 0) {
					unripe++;
				}
			}
		}

		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int answer = -1, ripe = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			answer = cur[2];

			for (int[] d : dir) {
				int nr = cur[0] + d[0];
				int nc = cur[1] + d[1];

				if (checkRange(map, nr, nc) && map[nr][nc] == 0) {
					ripe++;
					map[nr][nc] = 1;
					queue.offer(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}

		System.out.println(unripe == ripe ? answer : -1);
	}

	private static boolean checkRange(int[][] map, int r, int c) {
		return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
	}
}
