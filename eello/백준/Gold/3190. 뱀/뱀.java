import java.io.*;
import java.util.*;

public class Main {

    private static final int EMPTY = 0, SNAKE = 1, APPLE = 2;
	private static final int U = 0, R = 1, D = 2, L = 3;
	private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n + 1][n + 1];
		map[1][1] = SNAKE;

		int k = Integer.parseInt(br.readLine());
		while (k-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = APPLE;
		}

		Queue<int[]> change = new ArrayDeque<>();
		int l = Integer.parseInt(br.readLine());
		while (l-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = "D".equals(st.nextToken()) ? 1 : 3; // 1 : 시계 회전, 3 : 반시계 회전
			change.offer(new int[] {s, d});
		}

		Deque<int[]> snake = new ArrayDeque<>();
		snake.offerFirst(new int[]{1, 1, R});
		int sec = 1;
		while (!snake.isEmpty()) {
			int[] cur = snake.peekFirst();
			int d = !change.isEmpty() && change.peek()[0] < sec ? (cur[2] + change.poll()[1]) % 4 : cur[2];
			int nr = cur[0] + DIR[d][0];
			int nc = cur[1] + DIR[d][1];

			if (!checkRange(n, nr, nc) || map[nr][nc] == SNAKE) {
				break;
			}

			snake.offerFirst(new int[] {nr, nc, d});
			if (map[nr][nc] == EMPTY && !snake.isEmpty()) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = EMPTY;
			}

			map[nr][nc] = SNAKE;
			sec++;
		}

		System.out.println(sec);
	}

	private static boolean checkRange(int n, int r, int c) {
		return 0 < r && r <= n && 0 < c && c <= n;
	}
}
