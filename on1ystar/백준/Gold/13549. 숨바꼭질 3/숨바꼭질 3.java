import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
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
            Arrays.fill(dp, 100_001);
            dp[n] = 0;
            q.offer(n);

            while (!q.isEmpty()) {
                int x = q.poll();
                if (x == k) {
                    System.out.println(Math.min(dp[k], dp[k + 1]));
                    break;
                }

                int[] next = {x * 2, x + 1, x - 1};

                for (int i = 0; i < next.length; i++) {
                    if (next[i] > 0 && next[i] <= k) {
                        int nextTime = 0;
                        if (i == 0) { nextTime = dp[x]; }
                        else { nextTime = dp[x] + 1; }

                        if (nextTime < dp[next[i]]) {
                            dp[next[i]] = nextTime;
                            q.offer(next[i]);
                        }
                    } else if (next[i] > k) {
                        dp[k + 1] = Math.min(dp[k + 1], dp[x] + (next[i] - k));
                    }
                }
            }
        }
    }
}