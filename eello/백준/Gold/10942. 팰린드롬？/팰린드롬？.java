import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] seq = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder answer = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (isPalindrome(seq, s, e)) answer.append(1);
			else answer.append(0);
			answer.append("\n");
		}

		System.out.println(answer);
	}

	private static boolean isPalindrome(int[] seq, int s, int e) {
		while (s <= e) {
			if (seq[s++] != seq[e--]) {
				return false;
			}
		}

		return true;
	}
}
