import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		int n, k;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[] dp = new int[k + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			for (int j = k; j >= w; j--) {
				dp[j] = Math.max(dp[j], dp[j - w] + v);
			}
		}

		System.out.println(dp[k]);
	}
}
