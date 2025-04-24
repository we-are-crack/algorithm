import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer input = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        int i = 0;

        while (++i <= n) {
            int h = Integer.parseInt(input.nextToken());

            while (!stack.isEmpty() && stack.peek()[0] < h) {
                stack.pop();
            }

            int receive = stack.isEmpty() ? 0 : stack.peek()[1];
            answer.append(receive).append(" ");

            stack.push(new int[]{h, i});
        }

        System.out.print(answer);
    }
}
