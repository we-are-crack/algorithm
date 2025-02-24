import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seq = new int[n], dp = new int[n];
        int lis = 0;
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(br.readLine());
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            lis = Math.max(lis, dp[i]);
        }
        
        System.out.println(n - lis);
    }
}
