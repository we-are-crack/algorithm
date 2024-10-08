import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		while (t-- > 0) {
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();

			Stack<Integer> stack = new Stack<>();
			for (char ch : str.toCharArray()) {
				if (ch == 'A') {
					stack.push(0);
				} else {
					int cnt = 0;
					if (stack.size() > 1 && stack.peek() == 1) {
						while (stack.size() > 1 && stack.peek() == 1) {
							cnt++;
							stack.pop();
							stack.pop();
						}
					}

					stack.push(stack.isEmpty() ? 1 : stack.peek() + 1);
					while (cnt-- > 0) {
						stack.push(0);
					}
				}
			}

			for (Integer i : stack) {
				answer.append(i == 0 ? 'A' : 'B');
			}
			answer.append('\n');
		}

		System.out.print(answer);
	}
}
