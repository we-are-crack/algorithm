import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] city = new int[n][n];
		List<int[]> home = new ArrayList<>();
		List<int[]> chicken = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int type = Integer.parseInt(st.nextToken());
				if (type == 1) {
					home.add(new int[] {i, j});
				} else if (type == 2) {
					chicken.add(new int[] {i, j, 1});
				}
			}
		}

		System.out.println(solution(home, 0, chicken, chicken.size(), m));
	}

	private static int solution(List<int[]> home, int idx, List<int[]> chickenRestaurant, int chickenRestaurantCnt, int m) {
		if (chickenRestaurantCnt == m) {
			return minChickenDistance(home, chickenRestaurant);
		}

		if (idx == chickenRestaurant.size()) {
			return Integer.MAX_VALUE;
		}

		int answer = solution(home, idx + 1, chickenRestaurant, chickenRestaurantCnt, m);

		int[] cr = chickenRestaurant.get(idx);
		cr[2] = 0;
		answer = Math.min(answer, solution(home, idx + 1, chickenRestaurant, chickenRestaurantCnt - 1, m));
		cr[2] = 1;

		return answer;
	}

	private static int minChickenDistance(List<int[]> home, List<int[]> chicken) {
		int distance = 0;
		for (int[] h : home) {
			distance += minDistBetween(h, chicken);
		}

		return distance;
	}

	private static int minDistBetween(int[] home, List<int[]> chicken) {
		int minDistance = Integer.MAX_VALUE;
		for (int[] cr : chicken) {
			if (cr[2] == 0) continue;
			minDistance = Math.min(minDistance, Math.abs(home[0] - cr[0]) + Math.abs(home[1] - cr[1]));
		}
		return minDistance;
	}
}
