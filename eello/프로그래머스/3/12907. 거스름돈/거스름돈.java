import java.util.*;

class Solution {
    
    private static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        Arrays.sort(money);
        
        int[] dp = new int[n + 1];
        for (int m : money) {
            dp[m] += 1;
            
            for (int i = m; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - m]) % MOD;
            }
        }
        
        return dp[n];
    }
}