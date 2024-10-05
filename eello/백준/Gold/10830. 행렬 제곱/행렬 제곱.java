import java.io.*;
import java.util.*;

public class Main {

    private static final int MOD = 1_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
			}
		}

		int[][] result = pow(matrix, b);

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer.append(result[i][j]).append(" ");
			}
			answer.append("\n");
		}

		System.out.println(answer);
	}

	private static int[][] pow(int[][] matrix, long n) {
		if (n == 1) {
			return matrix;
		}

		int[][] temp = pow(matrix, n / 2);
		int[][] result = multiply(temp, temp);
		return n % 2 == 0 ? result : multiply(matrix, result);
	}

	private static int[][] multiply(int[][] a, int[][] b) {
		int size = a.length;
		int[][] c = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}

				c[i][j] %= MOD;
			}
		}

		return c;
	}
}
