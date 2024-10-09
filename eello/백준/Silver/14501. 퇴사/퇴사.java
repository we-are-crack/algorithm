import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] t = new int[n], p = new int[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution(t, p, 0, 0));
	}

	private static int solution(int[] t, int[] p, int day, int pay) {
		if (t.length <= day) {
			return pay;
		}

		int next = day + t[day];
		return Math.max(
			solution(t, p, next, next <= t.length ? pay + p[day] : pay),
			solution(t, p, day + 1, pay)
		);
	}
}
