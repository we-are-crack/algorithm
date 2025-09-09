import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[n];
        List<Integer>[] dp = new ArrayList[n]; // dp[i] = i번째 숫자를 포함한 가장 긴 증가하는 부분 수열
        List<Integer> lis = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            dp[i] = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i] && dp[i].size() < dp[j].size()) {
                    dp[i] = dp[j];
                }
            }

            dp[i] = new ArrayList<>(dp[i]);
            dp[i].add(seq[i]);

            if (lis.size() < dp[i].size()) {
                lis = dp[i];
            }
        }

        System.out.println(lis.size());
        StringBuilder sb = new StringBuilder();
        for (Integer elem : lis) {
            sb.append(elem).append(" ");
        }
        System.out.println(sb);
    }
}
