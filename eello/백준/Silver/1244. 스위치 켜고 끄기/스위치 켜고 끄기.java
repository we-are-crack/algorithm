import java.io.*;
import java.util.*;

public class Main {

    private static int[] status;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        status = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken()), num = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                changeByBoy(num);
            } else changeByGirl(num);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            answer.append(status[i]);
            if (i % 20 == 0) {
                answer.append("\n");
            } else answer.append(" ");
        }

        System.out.print(answer);
    }

    private static void changeByBoy(int n) {
        int i = 1;
        while (n * i < status.length) {
            toggle(n * (i++));
        }
    }

    private static void changeByGirl(int n) {
        int left = n - 1, right = n + 1;

        toggle(n);
        while (0 < left && right < status.length && status[left] == status[right]) {
            toggle(left--);
            toggle(right++);
        }
    }

    private static void toggle(int n) {
        status[n] = (status[n] + 1) % 2;
    }
}
