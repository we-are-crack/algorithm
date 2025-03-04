import java.io.*;
import java.util.*;

public class Main {

    private static final char[] OPERATOR = {' ', '+', '-'};
    private static Set<String> set;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            set = new TreeSet<>();
            dfs(1, new char[n - 1]);

            for (String s : set) {
                answer.append(s).append("\n");
            }

            answer.append("\n");
        }

        System.out.print(answer);
    }

    private static void dfs(int k, char[] operations) {
        if (k == n) {
            if (isZero(operations)) {
                StringBuilder sb = new StringBuilder("1");
                for (int i = 2; i <= n; i++) {
                    sb.append(operations[i - 2]).append(i);
                }
                set.add(sb.toString());
            }

            return;
        }

        for (char o : OPERATOR) {
            operations[k - 1] = o;
            dfs(k + 1, operations);
        }
    }

    private static boolean isZero(char[] operations) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        for (int i = 2; i <= n; i++) {
            if (operations[i - 2] == ' ') {
                deque.addLast(deque.pollLast() * 10 + i);
            } else {
                deque.addLast(i);
            }
        }

        int result = deque.pollFirst();
        for (int i = 0; i < n - 1; i++) {
            switch (operations[i]) {
                case '+':
                    result += deque.pollFirst();
                    break;

                case '-':
                    result -= deque.pollFirst();
                    break;

                default:
                    break;
            }
        }

        return result == 0;
    }
}
