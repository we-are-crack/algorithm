import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] dp = new int[k + 1];
		dp[0] = 1;
		while (n-- > 0) {
			int token = Integer.parseInt(br.readLine());
			for (int i = token; i <= k; i++) {
				dp[i] += dp[i - token];
			}
		}

		System.out.println(dp[k]);
	}
}
