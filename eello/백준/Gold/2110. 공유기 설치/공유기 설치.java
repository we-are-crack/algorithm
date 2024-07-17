import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] house = new int[n];
		for (int i = 0; i < n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(house);

		int answer = 0;
		int s = 1, e = house[house.length - 1];
		while (s <= e) {
			int m = (s + e) / 2;
			
			if (isPossible(house, c - 1, m)) {
				s = m + 1;
				answer = m;
			} else {
				e = m - 1;
			}
		}

		System.out.println(answer);
	}

	private static boolean isPossible(int[] house, int count, int distance) {
		int prev = house[0];
		for (int i = 1; i < house.length; i++) {
			if (distance <= house[i] - prev) {
				count--;
				prev = house[i];
			}

			if (count == 0) return true;
		}
		return false;
	}
}
