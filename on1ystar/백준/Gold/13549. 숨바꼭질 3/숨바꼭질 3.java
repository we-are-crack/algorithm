import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (k <= n) {
            System.out.println(n - k);
        } else {
            Queue<Integer> q = new LinkedList<>();
            int[] dp = new int[k + 2];
            dp[n] = 1;
            dp[k + 1] = 100_001;
            q.offer(n);

            while (!q.isEmpty()) {
                int x = q.poll();
                if (x == k) {
                    System.out.println(Math.min(dp[k], dp[k + 1]) - 1);
                    break;
                }

                if (x * 2 <= k && (dp[x * 2] == 0 || dp[x * 2] > dp[x])) {
                    dp[x * 2] = dp[x];
                    q.offer(x * 2);
                } else if (x * 2 > k) {
                    dp[k + 1] = Math.min(dp[k + 1], dp[x] + (x * 2 - k));
                }

                if (dp[x + 1] == 0 || dp[x + 1] > dp[x] + 1) {
                    dp[x + 1] = dp[x] + 1;
                    q.offer(x + 1);
                }

                if (x - 1 > 0 && (dp[x - 1] == 0 || dp[x - 1] > dp[x] + 1)) {
                    dp[x - 1] = dp[x] + 1;
                    q.offer(x - 1);
                }
            }
        }
    }
}