import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		L1: while (t-- > 0) {
			String command = br.readLine();
			int len = Integer.parseInt(br.readLine());
			int[] arr = convertArr(br.readLine(), len);

			int head = 0, tail = arr.length - 1, d = 1;
			for (char cmd : command.toCharArray()) {
				if (cmd == 'R') {
					d *= -1;
				} else {
					if (head > tail) {
						answer.append("error\n");
						continue L1;
					}

					if (d == 1) {
						head++;
					} else tail--;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if (d == 1) {
				for (int i = head; i <= tail; i++) {
					sb.append(arr[i]);
					if (i != tail) {
						sb.append(",");
					}
				}
			} else {
				for (int i = tail; i >= head; i--) {
					sb.append(arr[i]);
					if (i != head) {
						sb.append(",");
					}
				}
			}
			sb.append("]");

			answer.append(sb).append("\n");
		}

		System.out.print(answer);
	}

	private static int[] convertArr(String arr, int len) {
		String[] elem = arr.substring(1, arr.length() - 1).split(",");
		int[] a = new int[len];
		for (int i = 0; i < len; i++) {
			a[i] = Integer.parseInt(elem[i]);
		}

		return a;
	}
}
