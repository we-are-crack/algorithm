import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int days = Integer.parseInt(br.readLine());
        int[] dp = new int[days + 1];

        String[][] tp = new String[days + 1][2];
        for(int i = 1; i <= days; i++) {
            tp[i] = br.readLine().split(" ");
            int candidate = 0;
            for(int j = 1; j < i; j++) {
                if(Integer.parseInt(tp[j][0]) <= i - j)
                    candidate = Math.max(candidate, dp[j]);
            }
            if(Integer.parseInt(tp[i][0]) <= days - i + 1)
                dp[i] = Integer.parseInt(tp[i][1]) + candidate;
            else
                dp[i] = candidate;
        }
        Arrays.stream(dp).max().ifPresent(System.out::println);
    }
}